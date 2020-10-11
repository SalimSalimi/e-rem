package dz.salim.salimi.e_rem.data.repositories

import dz.salim.salimi.e_rem.data.models.Course
import dz.salim.salimi.e_rem.data.remote.FirebaseDatabase

class CourseRepository {

    fun addCourse(course: Course) {
        FirebaseDatabase.addCourse(course)
    }

    fun getAllCourses(onGetCourse: ((List<Course>) -> Unit)){
        FirebaseDatabase.getAllCourses(onGetCourse)
    }
}