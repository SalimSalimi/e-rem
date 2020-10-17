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

    private val _onLoginResponse = MutableLiveData<Boolean?>()
    val onLoginResponse: LiveData<Boolean?>
        get() = _onLoginResponse

    private val _navigateToRegister = MutableLiveData<Boolean?>()
    val navigateToRegister: LiveData<Boolean?>
        get() = _navigateToRegister

    init {
        _login.value = Login("h@gmail.com", "sasa42+")
    }

    private fun login() {
        AuthRepository.loginUser(_login.value!!) { success: Boolean?, exception: Exception? ->
            Log.d("LoginViewModel", "${exception?.message}")
            _onLoginResponse.postValue(success)
        }
    }

    fun onLoginClick() {
        login()
    }

    fun onRegisterClick() {
        this._navigateToRegister.postValue(true)
    }

    fun doneLogin() {
        this._onLoginResponse.value = null
    }

    fun doneNavigatingRegister() {
        this._navigateToRegister.value = null
    }
}