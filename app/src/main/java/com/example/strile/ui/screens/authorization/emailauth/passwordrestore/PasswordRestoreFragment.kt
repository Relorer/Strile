package com.example.strile.ui.screens.authorization.emailauth.passwordrestore

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import com.example.strile.R
import com.example.strile.ui.screens.authorization.AuthActivity
import com.example.strile.ui.screens.authorization.emailauth.EmailAuthFragment
import com.example.strile.utilities.ToastUtilities
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.email_auth_fragment.*

class PasswordRestoreFragment : Fragment() {

    companion object {
        fun getBundle(): Bundle {
            return Bundle()
        }

        fun newInstance() = PasswordRestoreFragment()
    }

    private lateinit var viewModel: PasswordRestoreViewModel
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        auth = Firebase.auth
        return inflater.inflate(R.layout.password_restore_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()

        text_title.text = "Password restore"
        edit_text_email.hint = "Email"
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
            auth.sendPasswordResetEmail(edit_text_email.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        (activity as AuthActivity).navController.popBackStack()
                    }
                    else {
                        ToastUtilities.showToast(this.requireActivity(), it.exception?.localizedMessage)
                    }
                }
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                validateAuthData()
            }

            override fun afterTextChanged(p0: Editable?) {}
        }

        edit_text_email.addTextChangedListener(textWatcher)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PasswordRestoreViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun validateAuthData() {
        if (isValidEmail(edit_text_email.text)) {
            button_next.isEnabled = true
            button_next.setTextColor(requireActivity().applicationContext.resources.getColor(R.color.colorAccent));
        } else {
            button_next.isEnabled = false
            button_next.setTextColor(requireActivity().applicationContext.resources.getColor(R.color.colorLightGray));
        }
    }

    private fun isValidEmail(target: CharSequence?): Boolean {
        return !TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches()
    }
}