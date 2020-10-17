package dz.salim.salimi.e_rem.ui.courselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.repositories.AuthRepository
import dz.salim.salimi.e_rem.data.repositories.CourseRepository

class CourseListViewModel: ViewModel() {

    private val _listCourses = MutableLiveData<List<Course>>()
    val listCourses : LiveData<List<Course>>
        get() = _listCourses

    init {
        getListCourses()
    }

    private fun getListCourses() {
        CourseRepository.getAllCoursesByUserId(_listCourses, AuthRepository.LOGGED_IN_USER_UID)
    }
}
