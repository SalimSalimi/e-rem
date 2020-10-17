package dz.salim.salimi.e_rem.ui.courselist

import android.util.Log
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

    private val _selectedCourseId = MutableLiveData<String?>()
    val selectedCourseId: LiveData<String?>
        get() = _selectedCourseId

    private val _navigateToAddCourse = MutableLiveData<Boolean?>()
    val navigateToAddCourse: LiveData<Boolean?>
        get() = _navigateToAddCourse

    init {
        initListCourses()
    }

    private fun initListCourses() {
        CourseRepository.getAllCoursesByUserId(_listCourses, AuthRepository.LOGGED_IN_USER_UID)
    }

    fun onAddBtnClicked() {
        _navigateToAddCourse.value = true
        Log.d("CourseListViewModel", "clicked")
    }

    fun onSetCourseId(courseId: String?) {
        _selectedCourseId.value = courseId
        _navigateToAddCourse.value = true
    }

    fun doneNavigatingToAddCourse() {
        _navigateToAddCourse.value = null
        _selectedCourseId.value = null
    }

}
