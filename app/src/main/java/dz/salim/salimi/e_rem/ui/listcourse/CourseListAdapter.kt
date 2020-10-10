package dz.salim.salimi.e_rem.ui.listcourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dz.salim.salimi.e_rem.data.models.Course
import dz.salim.salimi.e_rem.databinding.CourseItemBinding

class CourseListAdapter (private val courseList: List<Course>): RecyclerView.Adapter<CourseListAdapter.CourseListVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseListVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = CourseItemBinding.inflate(inflater, parent, false)
        return CourseListVH(itemBinding.root, itemBinding)
    }

    override fun onBindViewHolder(holder: CourseListVH, position: Int) {
        val courseItem = courseList[position]
        holder.bind(courseItem)
    }

    override fun getItemCount(): Int =
        courseList.size

    inner class CourseListVH(itemView: View, private val binding: CourseItemBinding): RecyclerView.ViewHolder(itemView) {

        fun bind(courseItem: Course) {
            binding.course = courseItem
            binding.executePendingBindings()
        }
    }
}