package dz.salim.salimi.e_rem.ui.addcourse

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dz.salim.salimi.e_rem.data.models.content.Course
import dz.salim.salimi.e_rem.data.repositories.CourseRepository
import dz.salim.salimi.e_rem.utils.getCurrentTime

class AddCourseViewModel : ViewModel() {

    private val _course = MutableLiveData<Course>()
    val course : LiveData<Course>
        get() = _course

    init {
        _course.value = Course()
        _course.value?.creatorId = Firebase.auth.currentUser!!.uid
    }

    fun onAddBtnClicked() {
        _course.value?.createdDate = getCurrentTime()
        CourseRepository.addCourse(_course.value!!)
    }
}