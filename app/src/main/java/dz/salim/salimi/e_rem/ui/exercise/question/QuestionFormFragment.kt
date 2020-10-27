package dz.salim.salimi.e_rem.ui.exercise.question

import android.app.Dialog
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.QuestionFormFragmentBinding
import dz.salim.salimi.e_rem.ui.exercise.question.OptionsList.OptionListAdapter
import kotlinx.android.synthetic.main.dialog_custom.*

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
        binding.addOptionBtn.setOnClickListener {
            alert()
        }
        setupRecyclerView()
    }

    private fun alert() {
        val view = layoutInflater.inflate(R.layout.dialog_custom, null, false)
        val optionEditText = view.findViewById<TextInputEditText>(R.id.option_title_edit_input)
        val isCorrectCheckBox = view.findViewById<MaterialCheckBox>(R.id.is_correct_check)

        MaterialAlertDialogBuilder(requireContext())
            .setTitle(resources.getString(R.string.add_an_option))
            .setMessage("Insert a new option and check the box if it's true or not!")
            .setView(view)
            .setPositiveButton(resources.getString(R.string.confirm)){ _, _ ->
                val option = optionEditText.text.toString()
                val isCorrect = isCorrectCheckBox.isChecked

                viewModel.addOptions(option, isCorrect)
            }
            .setNegativeButton(resources.getString(R.string.cancel)){ dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    private fun setupRecyclerView() {
        val layoutManager = LinearLayoutManager(context)

        val adapter = OptionListAdapter(viewModel.listOptions)
        binding.optionsListRecycler.layoutManager = layoutManager
        binding.optionsListRecycler.adapter = adapter
    }
}