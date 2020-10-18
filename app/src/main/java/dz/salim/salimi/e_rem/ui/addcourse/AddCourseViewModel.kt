package dz.salim.salimi.e_rem.ui.addcourse

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.repositories.AuthRepository
import dz.salim.salimi.e_rem.data.repositories.CourseRepository
import dz.salim.salimi.e_rem.utils.getCurrentTime

class AddCourseViewModel (val courseId: String?): ViewModel() {

    private val _course = MutableLiveData<Course>()
    val course : LiveData<Course>
        get() = _course

    init {
        initCourse()
    }

    fun onAddBtnClicked() {
        if (courseId == null) {
            _course.value?.createdDate = getCurrentTime()
            CourseRepository.addCourse(_course.value!!)
        } else {
            _course.value?.updatedDate = getCurrentTime()
            CourseRepository.updateCourse(_course.value!!)
        }
    }

    private fun initCourse() {
        if (courseId == null) {
            _course.value = Course()
            _course.value?.creatorId = AuthRepository.LOGGED_IN_USER_UID
        } else {
            getCourseById()
        }
    }

    private fun getCourseById() {
        CourseRepository.getCourseById(_course, courseId!!)
    }
}