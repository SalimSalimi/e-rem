package dz.salim.salimi.e_rem.ui.addcourse

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.Course
import dz.salim.salimi.e_rem.data.remote.FirebaseDatabase
import dz.salim.salimi.e_rem.utils.getCurrentTime

class AddCourseViewModel : ViewModel() {
    var context: Context? = null
    private val _course = MutableLiveData<Course>()
    val course : LiveData<Course>
        get() = _course

    init {
        _course.value = Course()
    }

    fun onAddBtnClicked() {
        _course.value?.createdDate = getCurrentTime()

        FirebaseDatabase.addCourse(_course.value!!)

        Toast.makeText(context!!, "${_course.value!!.title} and ${_course.value!!.description} and ${_course.value!!.createdDate}",Toast.LENGTH_LONG).show()

    }
}