package dz.salim.salimi.e_rem.ui.courselist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dz.salim.salimi.e_rem.R

class CourseListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_course_list, container, false)
        recyclerView = rootView.findViewById(R.id.course_list)
        setupRecyclerView()

        return rootView
    }

    private fun setupRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = linearLayoutManager

        val viewModel = ViewModelProvider(this).get(CourseListViewModel::class.java)
        viewModel.getListCourses()
        val adapter = CourseListAdapter(viewModel)
        recyclerView.adapter = adapter
    }
}