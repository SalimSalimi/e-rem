package dz.salim.salimi.e_rem.ui.courselist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.repositories.CourseRepository

class CourseListViewModel: ViewModel() {

    private val _listCourses = MutableLiveData<List<Course>>()

    val listCourses : LiveData<List<Course>>
        get() = _listCourses

    fun getListCourses() {
        val repository = CourseRepository()
        _listCourses.value = ArrayList()
        repository.getAllCourses {
            _listCourses.value = it
        }
    }
}
