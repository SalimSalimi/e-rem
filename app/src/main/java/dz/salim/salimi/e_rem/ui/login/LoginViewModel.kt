package dz.salim.salimi.e_rem.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dz.salim.salimi.e_rem.data.models.user.Login
import dz.salim.salimi.e_rem.data.repositories.AuthRepository
import java.lang.Exception

class LoginViewModel: ViewModel() {

    private val _login = MutableLiveData<Login>()
    val login: LiveData<Login>
        get() = _login

    private val _onLoginSuccessful = MutableLiveData<Boolean?>()
    val onLoginSuccessful: LiveData<Boolean?>
        get() = _onLoginSuccessful

    init {
        _login.value = Login()
    }

    fun login() {
        AuthRepository.loginUser(_login.value!!) { success: Boolean?, exception: Exception? ->
            Log.d("LoginViewModel", "$success")
            _onLoginSuccessful.postValue(success)
        }
    }

    fun doneLogin() {
        this._onLoginSuccessful.value = null
    }
}