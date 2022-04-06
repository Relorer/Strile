package com.example.strile.ui.screens.authorization.emailauth

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.res.ResourcesCompat
import com.example.strile.R
import com.example.strile.ui.screens.authorization.AuthActivity
import com.example.strile.utilities.AppConstants.Companion.EMAIL
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.app_bar_layout.*
import kotlinx.android.synthetic.main.email_auth_fragment.*

class EmailAuthPassFieldFragment : Fragment() {

    companion object {
        fun getBundle(email: String): Bundle {
            val bundle = Bundle()
            bundle.putString(EMAIL, email)
            return bundle
        }

        fun newInstance(email: String) = EmailAuthPassFieldFragment().apply { arguments = getBundle(email) }
    }

    private lateinit var viewModel: EmailAuthPassFieldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.email_auth_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmailAuthPassFieldViewModel::class.java)

    }

    override fun onResume() {
        super.onResume()

        image_special_purpose_button_left.setImageDrawable(
            ResourcesCompat.getDrawable(getResources(),
                R.drawable.back_arrow, null));

        image_special_purpose_button_left.setOnClickListener() {
            (activity as AuthActivity).navController.popBackStack()
        }

        edit_text.requestFocus();
//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)


        text_text_streak.text = "Sign up"
        edit_text.hint = "password"
        edit_text.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        button_next.text = "Sign up"
    }

}