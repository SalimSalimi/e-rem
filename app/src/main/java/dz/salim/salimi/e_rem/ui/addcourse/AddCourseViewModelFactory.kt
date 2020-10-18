package dz.salim.salimi.e_rem.ui.addcourse

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class AddCourseViewModelFactory (private val courseId: String?): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(AddCourseViewModel::class.java))
            return AddCourseViewModel(courseId) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}