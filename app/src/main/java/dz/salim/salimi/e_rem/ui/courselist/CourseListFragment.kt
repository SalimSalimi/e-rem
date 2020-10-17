package dz.salim.salimi.e_rem.ui.courselist

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.databinding.FragmentCourseListBinding

class CourseListFragment : Fragment() {

    private lateinit var viewModel: CourseListViewModel
    private lateinit var adapter: CourseListAdapter
    private lateinit var binding: FragmentCourseListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_course_list, container, false)

        setupRecyclerView()
        return binding.root
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        binding.courseList.layoutManager = linearLayoutManager

        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        val listener = CourseListener{
            viewModel.onSetCourseId(it)
        }

        adapter = CourseListAdapter(viewModel, listener)
        binding.courseListViewModel = viewModel
        observeListCourses()
        observeNavigationToAddCourse()

        binding.courseList.adapter = adapter
    }

    private fun observeListCourses() {
        viewModel.listCourses.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })
    }

    private fun observeNavigationToAddCourse() {
        viewModel.navigateToAddCourse.observe(viewLifecycleOwner) {
            if (it == true){
                navigateToAddCourse()
                Log.d("CourseListFragment", "clicked")
            }
        }
        viewModel.doneNavigatingToAddCourse()
    }

    private fun navigateToAddCourse() {
        val direction = CourseListFragmentDirections.actionCourseListFragmentToAddCourseFragment(viewModel.selectedCourseId.value)
        this.findNavController().navigate(direction)
    }

}