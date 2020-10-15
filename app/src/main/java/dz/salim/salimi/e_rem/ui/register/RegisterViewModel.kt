package dz.salim.salimi.e_rem.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dz.salim.salimi.e_rem.data.models.user.Login
import dz.salim.salimi.e_rem.data.models.user.Teacher
import dz.salim.salimi.e_rem.data.repositories.AuthRepository

class RegisterViewModel : ViewModel() {

    private val TAG: String = "RegisterViewModel"

    private val _teacher = MutableLiveData<Teacher>()
    val teacher : LiveData<Teacher>
        get() = _teacher

    private val _login = MutableLiveData<Login>()
    val login : LiveData<Login>
        get() = _login

    private val _onRegisterResponse = MutableLiveData<Boolean?>()
    val onRegisterResponse: LiveData<Boolean?>
        get() = _onRegisterResponse

    init {
        _teacher.value = Teacher()
        _login.value = Login()
    }

    fun register() {
        AuthRepository.registerUser(_teacher.value!!, _login.value!!) { response: Boolean?, exception: Exception? ->
            _onRegisterResponse.postValue(response)
            Log.d(TAG, "register: ${exception?.message}" )
        }
    }

    fun doneRegister() {
        _onRegisterResponse.value = null
    }
}