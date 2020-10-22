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

    private val selectedCourses = ArrayList<Course>()

    private val _numberSelectedCourses = MutableLiveData<Int>()
    val numberSelectedCourses: LiveData<Int>
        get() = _numberSelectedCourses

    private val _showSnackBar = MutableLiveData<Boolean?>()
    val showSnackBar: LiveData<Boolean?>
        get() = _showSnackBar

    init {
        initListCourses()
        _numberSelectedCourses.value = 0
    }

    fun onFloatBtnAction() {
        if (_numberSelectedCourses.value == 0) {
            onAddBtn()
        } else {
            onDeleteBtn()
        }
    }

    private fun onAddBtn() {
        _navigateToAddCourse.value = true
    }

    private fun onDeleteBtn() {
        deleteCourses()
    }

    fun onSelectCourse(course: Course) {
        selectedCourses.add(course)
        _numberSelectedCourses.value = _numberSelectedCourses.value!!.inc()

        Log.d("CourseViewModel", "${_numberSelectedCourses.value}")
    }

    fun onUnSelectCourse(course: Course) {
        selectedCourses.remove(course)
        _numberSelectedCourses.value = _numberSelectedCourses.value!!.dec()
    }

    fun onSetCourseId(courseId: String?) {
        _selectedCourseId.value = courseId
        _navigateToAddCourse.value = true
    }

    fun doneNavigatingToAddCourse() {
        _navigateToAddCourse.value = null
        _selectedCourseId.value = null
    }

    fun doneShowSnackBar() {
        _showSnackBar.value = null
    }

    private fun initListCourses() {
        CourseRepository.getAllCoursesByUserId(_listCourses, AuthRepository.LOGGED_IN_USER_UID)
    }

    private fun deleteCourses() {
        if (selectedCourses.size > 0) {
            for (course in selectedCourses) {
                CourseRepository.deleteCourse(course)
                _numberSelectedCourses.value!!.times(0)
                _showSnackBar.value = true
            }
        }
    }
}
