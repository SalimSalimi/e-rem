package dz.salim.salimi.e_rem.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.user.Teacher

class RegisterViewModel : ViewModel() {

    private val _teacher = MutableLiveData<Teacher>()
    val teacher : LiveData<Teacher>
        get() = _teacher

    init {
        _teacher.value = Teacher()
    }
}