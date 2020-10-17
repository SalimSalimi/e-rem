package dz.salim.salimi.e_rem.data.repositories

import androidx.lifecycle.MutableLiveData
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.remote.DataFirebase
import dz.salim.salimi.e_rem.utils.COURSE_REF

object CourseRepository {

    fun addCourse(course: Course) {
        DataFirebase.addEntity(course, COURSE_REF)
    }

    fun updateCourse(course: Course) {
        DataFirebase.updateEntity(course, COURSE_REF)
    }

    fun deleteCourse(course: Course) {
        DataFirebase.deleteEntity(course, COURSE_REF)
    }

    fun getAllCourses(data: MutableLiveData<List<Course>>){
        DataFirebase.getAll(data, COURSE_REF)
    }

    fun getAllCoursesByUserId(data: MutableLiveData<List<Course>>, userId: String) {
        DataFirebase.getAllByFirstChild(data, COURSE_REF, "creatorId", userId)
    }
}