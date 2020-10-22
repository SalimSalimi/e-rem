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
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.card.MaterialCardView
import com.google.android.material.snackbar.Snackbar
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.databinding.FragmentCourseListBinding

class CourseListFragment : Fragment() {

    private lateinit var viewModel: CourseListViewModel
    private lateinit var adapter: CourseListAdapter
    private lateinit var binding: FragmentCourseListBinding
    private var numberSelectedCards = 0

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
        val gridLayoutManager = GridLayoutManager(context, 2)
        binding.courseList.layoutManager = gridLayoutManager

        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)

        val clickListener: ((courseId: String?) -> Unit) = {
            viewModel.onSetCourseId(it)
        }

        val longClickListener: ((view: View, course: Course) -> Boolean) = { view, course ->
            if (view is MaterialCardView) {
                onCheckedCard(course, view)
                view.toggle()
            }
            true
        }

        val listener = CourseListener(clickListener, longClickListener)

        adapter = CourseListAdapter(viewModel, listener)
        binding.courseListViewModel = viewModel

        observeListCourses()
        observeNavigationToAddCourse()
        observeNumberSelectedCourses()
        observeSnackBar()

        binding.courseList.adapter = adapter
    }

    private fun observeListCourses() {
        viewModel.listCourses.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })
    }

    private fun observeNavigationToAddCourse() {
        viewModel.navigateToAddCourse.observe(viewLifecycleOwner) {
            if (it == true) {
                navigateToAddCourse()
            }
        }
        viewModel.doneNavigatingToAddCourse()
    }

    private fun observeNumberSelectedCourses() {
        viewModel.numberSelectedCourses.observe(viewLifecycleOwner) {
            numberSelectedCards = it
            binding.courseList.adapter!!.notifyDataSetChanged()
        }
    }

    private fun observeSnackBar() {
        val snackbar = Snackbar.make(binding.root, "Are you sure to delete them ?", Snackbar.LENGTH_INDEFINITE)
            .setAction("Confirm") {
                viewModel.doneShowSnackBar()
            }

        viewModel.showSnackBar.observe(viewLifecycleOwner) {
            if (it == true) {
                snackbar.show()
            }

        }
    }

    private fun navigateToAddCourse() {
        val direction =
            CourseListFragmentDirections.actionCourseListFragmentToAddCourseFragment(viewModel.selectedCourseId.value)
        this.findNavController().navigate(direction)
    }

    private fun onCheckedCard(course: Course, view: MaterialCardView) {
        view.setOnCheckedChangeListener { card, isChecked ->

            if (isChecked) {
                viewModel.onSelectCourse(course)
            } else {
                viewModel.onUnSelectCourse(course)
            }
            toggleFloatButtonImage()
        }
    }

    private fun toggleFloatButtonImage() {
        when  {
            numberSelectedCards < 0 ->
                numberSelectedCards = 0
            numberSelectedCards >= 1 ->
                binding.addBtnCourse.setImageResource(R.drawable.ic_delete_24)
            numberSelectedCards == 0 ->
                binding.addBtnCourse.setImageResource(R.drawable.ic_add_24)
        }
    }

}