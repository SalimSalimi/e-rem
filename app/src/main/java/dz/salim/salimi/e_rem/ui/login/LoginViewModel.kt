package dz.salim.salimi.e_rem.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dz.salim.salimi.e_rem.data.models.user.Login

class LoginViewModel: ViewModel() {

    private val _login = MutableLiveData<Login>()
    val login: LiveData<Login>
        get() = _login

    init {
        _login.value = Login()
    }

    fun login() {
        val auth = Firebase.auth
        auth.signInWithEmailAndPassword(_login.value!!.email, _login.value!!.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val id = FirebaseAuth.getInstance().currentUser?.uid
                    Log.d("Login", "amchii $id")
                }
            }
    }
}