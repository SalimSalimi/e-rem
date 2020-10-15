package dz.salim.salimi.e_rem.ui.register

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var binding: FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_register, container, false)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        registerViewModel.onRegisterResponse.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController().navigate(
                    R.id.action_registerFragment_to_courseListFragment
                )
                registerViewModel.doneRegister()
            }
        }
        binding.registerViewModel = registerViewModel
        return binding.root
    }

}