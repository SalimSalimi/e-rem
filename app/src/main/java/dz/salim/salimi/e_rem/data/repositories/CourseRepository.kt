package dz.salim.salimi.e_rem.data.repositories

import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.remote.DataFirebase
import dz.salim.salimi.e_rem.utils.COURSE_REF

class CourseRepository {

    fun addCourse(course: Course) {
        DataFirebase.addEntity(course, COURSE_REF)
    }

    fun getAllCourses(onGetCourse: ((List<Course>) -> Unit)){
        DataFirebase.getAllCourses(onGetCourse)
    }

    fun updateCourse(course: Course) {
        DataFirebase.updateCourse(course)
    }

    fun deleteCourse(courseKey: String) {
        DataFirebase.deleteCourse(courseKey)
    }
}