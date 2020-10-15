package dz.salim.salimi.e_rem.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_login,
            container, false)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        onLoginObserve()
        onRegisterNavigationObserve()

        binding.loginViewModel = viewModel
        return binding.root
    }

    private fun onRegisterNavigationObserve() {
        viewModel.navigateToRegister.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_loginFragment_to_registerFragment
                )
                viewModel.doneNavigatingRegister()
            }
        }
    }

    private fun onLoginObserve() {
        viewModel.onLoginResponse.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_loginFragment_to_courseListFragment
                )
                viewModel.doneLogin()
            }
        }
    }
}