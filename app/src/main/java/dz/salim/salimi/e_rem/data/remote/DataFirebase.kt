package dz.salim.salimi.e_rem.data.remote

import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dz.salim.salimi.e_rem.data.models.Entity
import dz.salim.salimi.e_rem.data.models.content.Content
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.models.user.Teacher
import dz.salim.salimi.e_rem.data.models.user.User
import dz.salim.salimi.e_rem.utils.COURSE_REF
import dz.salim.salimi.e_rem.utils.TEACHER_REF

object DataFirebase {

    private val database = Firebase.database
    private val reference = database.reference

    fun <T : Entity> addEntity(entity: T, firebaseRef: String): Task<Void>? {
        return when (entity) {
            is User -> {
                reference.child(firebaseRef).child(entity.id)
                    .setValue(entity)
            }
            is Content -> {
                val newEntity = reference.child(firebaseRef).push()
                entity.id = newEntity.key!!
                newEntity.setValue(entity)
            }
            else -> {
                return null
            }
        }
    }

    fun getAllCourses(onGetCourse: ((List<Course>) -> Unit)) {
        val listCourses = ArrayList<Course>()
        val coursesListener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listCourses.clear()
                snapshot.children.forEach { courseChild ->
                    val course = courseChild.getValue(Course::class.java)
                    listCourses.add(course!!)
                }
                onGetCourse(listCourses)
            }

            override fun onCancelled(error: DatabaseError) {
                onGetCourse(listCourses)
                Log.d("FIREBASE_ERROR", "Can't retrieve data ${error.toException().message}")
            }
        }
        reference.child(
            COURSE_REF).addValueEventListener(coursesListener)
    }

    fun updateCourse(course: Course) {
        reference.child(COURSE_REF).child(course.id).setValue(course)
    }

    fun deleteCourse(courseKey: String) {
        reference.child(COURSE_REF).child(courseKey).removeValue()
    }

}