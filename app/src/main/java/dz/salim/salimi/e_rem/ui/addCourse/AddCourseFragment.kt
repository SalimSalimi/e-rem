package dz.salim.salimi.e_rem.ui.addCourse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.AddCourseFragmentBinding

class AddCourseFragment : Fragment() {

    companion object {
        fun newInstance() = AddCourseFragment()
    }

    private lateinit var viewModel: AddCourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AddCourseFragmentBinding = DataBindingUtil.inflate(inflater ,R.layout.add_course_fragment,
                container, false)
        val addCourseViewModel = AddCourseViewModel()
        addCourseViewModel.context = context
        binding.addCourseViewModel = addCourseViewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AddCourseViewModel::class.java)
        
    }

}