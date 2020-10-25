package dz.salim.salimi.e_rem.ui.exercise.question

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.QuestionFormFragmentBinding

class QuestionFormFragment : Fragment() {

    private lateinit var binding: QuestionFormFragmentBinding
    private lateinit var viewModel: QuestionFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.question_form_fragment, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestionFormViewModel::class.java)
        binding.questionVm = viewModel
    }

}