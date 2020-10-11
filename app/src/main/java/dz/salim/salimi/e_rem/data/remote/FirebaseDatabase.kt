package dz.salim.salimi.e_rem.data.remote

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dz.salim.salimi.e_rem.data.models.Course
import dz.salim.salimi.e_rem.utils.COURSE_REF

object FirebaseDatabase {

    private val database = Firebase.database
    private val reference = database.reference

    fun addCourse(course: Course) {
        val newCourse = reference.child(COURSE_REF).push()
        course.id = newCourse.key!!
        newCourse.setValue(course)
    }
}