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

class RegisterViewModel : ViewModel() {

    private val _teacher = MutableLiveData<Teacher>()
    val teacher : LiveData<Teacher>
        get() = _teacher

    private val _login = MutableLiveData<Login>()
    val login : LiveData<Login>
        get() = _login

    init {
        _teacher.value = Teacher()
        _login.value = Login()
    }

    fun register() {
        val ref = Firebase.database.reference
        val auth = Firebase.auth
        auth.createUserWithEmailAndPassword(_login.value!!.email, _login.value!!.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val uid = auth.currentUser!!.uid
                    _teacher.value!!.id = uid
                    ref.child("Teacher").setValue(_teacher.value!!)
                } else {
                    Log.d("RegisterViewModel", "Unsuccessful")
                }
            }
    }
}