package com.example.strile.ui.screens.authorization.chooseauthway

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.strile.R
import com.example.strile.data_firebase.models.User
import com.example.strile.data_firebase.repositories.UserRepository
import com.example.strile.infrastructure.settings.UsersSettings
import com.example.strile.ui.screens.authorization.AuthActivity
import com.example.strile.ui.screens.authorization.emailauth.EmailAuthFragment
import com.example.strile.ui.screens.main.MainActivity
import com.example.strile.utilities.AppConstants.Companion.TAG_LOG
import com.google.android.gms.auth.UserRecoverableAuthException
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.choose_auth_way_fragment.*


class ChooseAuthWayFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseAuthWayFragment()
    }

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
                    firebaseAuthWithGoogle(account.idToken!!);
                }
            } catch (e: ApiException) {
                Log.d(TAG_LOG, "api exception")
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChooseAuthWayViewModel::class.java)
        auth = Firebase.auth

        val buttonEmailAuthLabel: Spannable = SpannableString("   Sign in with Email")
        val buttonGoogleAuthLabel: Spannable = SpannableString("   Sign in with Google")

        buttonEmailAuthLabel.setSpan(
            ImageSpan(
                requireActivity().applicationContext, R.drawable.ic_baseline_account_box_24,
                DynamicDrawableSpan.ALIGN_BOTTOM
            ), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        buttonGoogleAuthLabel.setSpan(
            ImageSpan(
                requireActivity().applicationContext, R.drawable.googleg_standard_color_18,
                ImageSpan.ALIGN_CENTER
            ), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        button_auth_email.text = buttonEmailAuthLabel
        button_auth_google.text = buttonGoogleAuthLabel

        button_auth_email.setOnClickListener() {openEmailAuth()}

        button_auth_google.setOnClickListener() {
            launcher.launch(getClient().signInIntent)
        }

        button_skip_auth.text = if (FirebaseAuth.getInstance().currentUser == null) "Skip" else "Back";
        button_skip_auth.setOnClickListener() {
            if (FirebaseAuth.getInstance().currentUser == null) {
                auth.signInAnonymously().addOnCompleteListener {
                    if (it.isSuccessful) {
                        openMainActivity()
                    } else {
                        //TODO
                        Log.w(TAG_LOG, "signInAnonymously:failure", it.exception)
                    }
                }
            }
            else {
                openMainActivity()
            }
        }
    }

    private fun getClient() : GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build();
        return GoogleSignIn.getClient(this.requireActivity(), gso);
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

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        if (FirebaseAuth.getInstance().currentUser == null) {
            auth.signInWithCredential(credential).addOnCompleteListener {
                if (it.isSuccessful) {
                    openMainActivity()
                }
                else {
                    Log.d(TAG_LOG, "google sign in error")
                }
            }
        }
        else {
            auth.currentUser!!.linkWithCredential(credential)
                .addOnCompleteListener{ task ->
                    if (task.isSuccessful) {
                        openMainActivity()
                    } else {
                        //TODO
//                        Log.w(TAG, "linkWithCredential:failure", task.exception)
//                        Toast.makeText(baseContext, "Authentication failed.",
//                            Toast.LENGTH_SHORT).show()
//                        updateUI(null)
                    }
                }
        }
    }


}