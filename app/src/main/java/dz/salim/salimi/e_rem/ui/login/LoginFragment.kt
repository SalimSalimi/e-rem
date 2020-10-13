package dz.salim.salimi.e_rem.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,
            container, false)

        val viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel
        return binding.root
    }
}