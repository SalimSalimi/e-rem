package dz.salim.salimi.e_rem.ui.courselist

import dz.salim.salimi.e_rem.data.models.content.Course

class CourseListener(val clickListener: (courseId: String?) -> Unit) {
    fun onClick(course: Course) = clickListener(course.id)
}