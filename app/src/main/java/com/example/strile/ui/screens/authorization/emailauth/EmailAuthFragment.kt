package com.example.strile.ui.screens.authorization.emailauth

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.strile.R
import com.example.strile.ui.screens.authorization.AuthActivity
import com.example.strile.ui.screens.main.MainActivity
import com.example.strile.utilities.AppConstants.Companion.TAG_LOG
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.email_auth_fragment.*


class EmailAuthFragment : Fragment() {

    companion object {
        fun getBundle(): Bundle {
            return Bundle()
        }

        fun newInstance() = EmailAuthFragment()
    }

    private lateinit var viewModel: EmailAuthEmailFieldViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        return inflater.inflate(R.layout.email_auth_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()

        text_title.text = "Sing in / Sign up"
        edit_text_email.hint = "Email"
        edit_text_pass.hint = "Password"
        button_next.text = "Confirm"

        image_special_purpose_button_left.setImageDrawable(
            ResourcesCompat.getDrawable(
                getResources(),
                R.drawable.back_arrow, null
            )
        );

        image_special_purpose_button_left.setOnClickListener() {
            (activity as AuthActivity).navController.popBackStack()
        }

        button_next.setOnClickListener() {
            trySignUp()
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateAuthData()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        edit_text_email.addTextChangedListener(textWatcher)
        edit_text_pass.addTextChangedListener(textWatcher)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmailAuthEmailFieldViewModel::class.java)
    }

    private fun validateAuthData() {
        if (isValidEmail(edit_text_email.text) && edit_text_pass.text.length > 6) {
            button_next.isEnabled = true
            button_next.setTextColor(requireActivity().applicationContext.resources.getColor(R.color.colorAccent));
        } else {
            button_next.isEnabled = false
            button_next.setTextColor(requireActivity().applicationContext.resources.getColor(R.color.colorLightGray));
        }
    }

    private fun openMainActivity() {
        startActivity(Intent(activity, MainActivity::class.java))
        activity?.finish()
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }

    private fun trySignUp() {
        auth.createUserWithEmailAndPassword(
            edit_text_email.text.toString(),
            edit_text_pass.text.toString()
        ).addOnCompleteListener() {
            if (it.isSuccessful) {
                trySingIn()
            }
            else {
                if (it.exception is FirebaseAuthWeakPasswordException) {

                } else if (it.exception is FirebaseAuthInvalidCredentialsException) {

                } else if (it.exception is FirebaseAuthUserCollisionException) {
                    trySingIn()
                } else {
                    Log.d(TAG_LOG, "email auth: " + it.exception?.message);
                }
            }
        }
    }

    private fun trySingIn() {
        auth.signInWithEmailAndPassword(
            edit_text_email.text.toString(),
            edit_text_pass.text.toString()
        ).addOnCompleteListener() {
            if (it.isSuccessful) {
                openMainActivity()
            }
        }
    }
}