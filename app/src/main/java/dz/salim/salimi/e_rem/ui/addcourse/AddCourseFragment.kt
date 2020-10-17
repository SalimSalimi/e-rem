package dz.salim.salimi.e_rem.ui.addcourse

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.AddCourseFragmentBinding

class AddCourseFragment : Fragment() {

    val args: AddCourseFragmentArgs by navArgs()
    private lateinit var viewModel: AddCourseViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: AddCourseFragmentBinding = DataBindingUtil.inflate(inflater ,R.layout.add_course_fragment,
                container, false)

        viewModel = ViewModelProvider(this).get(AddCourseViewModel::class.java)
        binding.addCourseViewModel = viewModel
        val arg = args.courseId
        Log.d("AddCourseFragment", "$arg")
        binding.lifecycleOwner = this
        return binding.root
    }

}