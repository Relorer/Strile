package com.example.strile.ui.screens.authorization.chooseauthway

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.strile.R
import com.example.strile.ui.screens.authorization.AuthActivity
import com.example.strile.ui.screens.authorization.emailauth.EmailAuthEmailFieldFragment
import kotlinx.android.synthetic.main.choose_auth_way_fragment.*

class ChooseAuthWayFragment : Fragment() {

    companion object {
        fun newInstance() = ChooseAuthWayFragment()
    }

    private lateinit var viewModel: ChooseAuthWayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.choose_auth_way_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChooseAuthWayViewModel::class.java)
        button_auth_email.setOnClickListener() {openEmailAuth()}

        button_auth_google.setOnClickListener() {

        }
    }

    override fun onResume() {
        super.onResume()

//        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
//        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

    private fun openEmailAuth() {
        (activity as AuthActivity).navController.navigate(
            com.example.strile.R.id.action_chooseAuthWayFragment_to_emailAuthEmailFieldFragment,
            EmailAuthEmailFieldFragment.getBundle()
        )
    }

}