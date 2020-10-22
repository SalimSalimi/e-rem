package dz.salim.salimi.e_rem.ui.courselist

import android.view.View
import dz.salim.salimi.e_rem.data.models.content.Course

class CourseListener(val clickListener: (courseId: String?) -> Unit,
                     val longLickListener: (view: View, course: Course) -> Boolean) {

    fun onClick(course: Course) = clickListener(course.id)
    fun onLongClick(view: View, course: Course): Boolean = longLickListener(view, course)
}