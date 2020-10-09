package dz.salim.salimi.e_rem.ui.addCourse

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.Course

class AddCourseViewModel : ViewModel() {
    var context: Context? = null
    private val _course = MutableLiveData<Course>()
    val course : LiveData<Course>
        get() = _course

    init {
        _course.value = Course()
    }

    fun onAddBtnClicked() {
        Toast.makeText(context!!, "${_course.value!!.title} and ${_course.value!!.description}",Toast.LENGTH_LONG).show()
    }
}