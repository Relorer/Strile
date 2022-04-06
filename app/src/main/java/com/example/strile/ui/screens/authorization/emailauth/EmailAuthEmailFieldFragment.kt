package com.example.strile.ui.screens.authorization.emailauth

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import com.example.strile.R
import com.example.strile.ui.screens.authorization.AuthActivity
import kotlinx.android.synthetic.main.app_bar_layout.image_special_purpose_button_left
import kotlinx.android.synthetic.main.email_auth_fragment.*
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener

class EmailAuthEmailFieldFragment : Fragment() {

    companion object {
        fun getBundle(): Bundle {
            return Bundle()
        }

        fun newInstance() = EmailAuthEmailFieldFragment()
    }

    private lateinit var viewModel: EmailAuthEmailFieldViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.email_auth_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()

        text_text_streak.text = "Sing in / Sign up"
        edit_text.hint = "Email"
        button_next.text = "Next"

//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        image_special_purpose_button_left.setImageDrawable(
            ResourcesCompat.getDrawable(getResources(),
            R.drawable.back_arrow, null));

        image_special_purpose_button_left.setOnClickListener() {
        (activity as AuthActivity).navController.popBackStack()
        }

        edit_text.requestFocus();
//        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EmailAuthEmailFieldViewModel::class.java)

        edit_text.addTextChangedListener() {
            button_next.isEnabled = it != null && it.isNotEmpty()
        }

        button_next.setOnClickListener() {
            openNextStepEmailAuth(edit_text.text.toString())
        }
    }

    private fun openNextStepEmailAuth(email: String) {
        (activity as AuthActivity).navController.navigate(
            R.id.action_emailAuthEmailFieldFragment_to_emailAuthPassFieldFragment,
            EmailAuthPassFieldFragment.getBundle(email)
        )
    }

}