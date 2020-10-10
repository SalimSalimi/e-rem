package dz.salim.salimi.e_rem.ui.listcourse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dz.salim.salimi.e_rem.R
import dz.salim.salimi.e_rem.data.models.Course
import kotlinx.android.synthetic.main.fragment_course_list.*

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

        val courseList = ArrayList<Course>()
        courseList.add(
            Course(5, "Hello World", "HEre we go", "06.06.06. 15:30",
                "06.06.06. 15:30", "Nothing"))
        courseList.add(
            Course(5, "Kebab", "Let's eat a kebab", "06.06.08. 15:30",
                "06.06.06. 15:30", "Nothing"))
        courseList.add(
            Course(5, "Why should i write ?", "Idk what to write", "06.06.07. 15:30",
                "06.06.06. 15:30", "Nothing"))

        val adapter = CourseListAdapter(courseList)
        recyclerView.adapter = adapter
    }
}