package dz.salim.salimi.e_rem.ui.courselist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dz.salim.salimi.e_rem.R
import kotlinx.android.synthetic.main.fragment_course_list.view.*

class CourseListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CourseListViewModel
    private lateinit var adapter: CourseListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_course_list, container, false)
        recyclerView = rootView.findViewById(R.id.course_list)

        rootView.button2.setOnClickListener {
            onAddBtnClicked()
        }

        setupRecyclerView()
        return rootView
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)
        adapter = CourseListAdapter(viewModel)

        observeListCourses()

        recyclerView.adapter = adapter
    }

    private fun observeListCourses() {
        viewModel.listCourses.observe(viewLifecycleOwner, {
            adapter.notifyDataSetChanged()
        })
    }

    private fun onAddBtnClicked() {
        this.findNavController().navigate(
            R.id.action_courseListFragment_to_addCourseFragment
        )
    }

}