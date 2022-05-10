package com.example.strile.ui.screens.authorization.chooseauthway

import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.DynamicDrawableSpan
import android.text.style.ImageSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.strile.R
import com.example.strile.data_firebase.repositories.UserRepository
import com.example.strile.ui.screens.authorization.AuthActivity
import com.example.strile.ui.screens.authorization.emailauth.EmailAuthFragment
import com.example.strile.ui.screens.main.MainActivity
import com.example.strile.utilities.AppConstants.Companion.TAG_LOG
import com.example.strile.utilities.ToastUtilities
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.choose_auth_way_fragment.*
import kotlinx.android.synthetic.main.email_auth_fragment.*


class ChooseAuthWayFragment : Fragment() {

    companion object;

    private lateinit var viewModel: ChooseAuthWayViewModel
    private lateinit var auth: FirebaseAuth
    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_auth_way_fragment, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if (account != null) {
                    val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                    if (FirebaseAuth.getInstance().currentUser == null) {
                        firebaseAuthWithGoogle(credential)
                    } else {
                        firebaseLinkWithGoogle(credential)
                    }
                }
            } catch (e: ApiException) {
                Log.d(TAG_LOG, "api exception")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[ChooseAuthWayViewModel::class.java]
        auth = Firebase.auth

        val buttonEmailAuthLabel: Spannable = SpannableString("   Sign in with Email")
        val buttonGoogleAuthLabel: Spannable = SpannableString("   Sign in with Google")

        buttonEmailAuthLabel.setSpan(
            ImageSpan(
                requireActivity().applicationContext, R.drawable.ic_baseline_account_box_24,
                DynamicDrawableSpan.ALIGN_BOTTOM
            ), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            buttonGoogleAuthLabel.setSpan(
                ImageSpan(
                    requireActivity().applicationContext, R.drawable.googleg_standard_color_18,
                    ImageSpan.ALIGN_CENTER
                ), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        else {
            buttonGoogleAuthLabel.setSpan(
                ImageSpan(
                    requireActivity().applicationContext, R.drawable.googleg_standard_color_18
                ), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        button_auth_email.text = buttonEmailAuthLabel
        button_auth_google.text = buttonGoogleAuthLabel

        button_auth_email.setOnClickListener { openEmailAuth() }

        button_auth_google.setOnClickListener {
            launcher.launch(getClient().signInIntent)
        }

        button_skip_auth.text =
            if (FirebaseAuth.getInstance().currentUser == null) "Skip" else "Back"
        button_skip_auth.setOnClickListener {
            if (FirebaseAuth.getInstance().currentUser == null) {
                auth.signInAnonymously().addOnCompleteListener {
                    if (it.isSuccessful) {
                        openMainActivity()
                    } else {
                        ToastUtilities.showToast(this.requireActivity(), it.exception?.localizedMessage)
                    }
                }
            } else {
                openMainActivity()
            }
        }
    }

    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        return GoogleSignIn.getClient(this.requireActivity(), gso)
    }

    private fun openEmailAuth() {
        (activity as AuthActivity).navController.navigate(
            R.id.action_chooseAuthWayFragment_to_emailAuthEmailFieldFragment,
            EmailAuthFragment.getBundle()
        )
    }

    private fun openMainActivity() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }

    private fun firebaseLinkWithGoogle(credential: AuthCredential) {
        auth.currentUser!!.linkWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    openMainActivity()
                } else {
                    if (it.exception is FirebaseAuthUserCollisionException) {
                        UserRepository().getCurrentUser {

                            UserRepository().deleteCurrentUser()

                            val post = {
                                AlertDialog.Builder(this.requireActivity())
                                    .setMessage("Перенести локальные данные на аккаунт?")
                                    .setPositiveButton("Yes") { dialogInterface: DialogInterface, _ ->
                                        UserRepository().mergeWithCurrent(it)
                                        openMainActivity()
                                        dialogInterface.dismiss()
                                    }
                                    .setNegativeButton("No") { dialogInterface: DialogInterface, _ ->
                                        openMainActivity()
                                        dialogInterface.dismiss()
                                    }.create().show()
                            }

                            val postFail = {
                                UserRepository().mergeWithCurrent(it)
                            }

                            firebaseAuthWithGoogle(credential, post, postFail)

                        }

                    } else {
                        ToastUtilities.showToast(this.requireActivity(), it.exception?.localizedMessage)
                    }
                }
            }
    }

    private fun firebaseAuthWithGoogle(
        credential: AuthCredential,
        post: (() -> Unit)? = null, postFail: (() -> Unit)? = null
    ) {
        auth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
                if (post != null) post()
                else openMainActivity()
            } else {
                if (postFail != null) postFail()
                ToastUtilities.showToast(this.requireActivity(), it.exception?.localizedMessage)
            }
        }
    }

}