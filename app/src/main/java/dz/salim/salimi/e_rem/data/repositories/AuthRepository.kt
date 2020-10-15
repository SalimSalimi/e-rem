package dz.salim.salimi.e_rem.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import com.google.firebase.auth.FirebaseAuth
import dz.salim.salimi.e_rem.data.models.user.Login
import dz.salim.salimi.e_rem.data.models.user.Teacher
import dz.salim.salimi.e_rem.data.remote.AuthFirebase
import dz.salim.salimi.e_rem.data.remote.DataFirebase
import java.lang.Exception

object AuthRepository {

    fun loginUser(login: Login, onLoginSuccess: ((Boolean?, Exception?) -> Unit)) {
        AuthFirebase.loginWithEmail(login.email, login.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onLoginSuccess(true, null)
                } else {
                    onLoginSuccess(false, null)
                }
                Log.d("AuthRepo", "${it.exception?.message}")
            }.addOnFailureListener {
                    onLoginSuccess(null, it)
            }
    }

    fun registerUser(teacher: Teacher, login: Login, onRegisterResponse: ((Boolean?, Exception?) -> Unit)) {
        AuthFirebase.registerWithEmail(login.email, login.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = FirebaseAuth.getInstance().currentUser!!.uid
                    teacher.id = uid
                    DataFirebase.addTeacher(teacher).addOnCompleteListener {
                        if (it.isSuccessful) {
                            onRegisterResponse(true, null)
                        } else {
                            onRegisterResponse(false, it.exception)
                        }
                    }
                } else {
                    onRegisterResponse(false, task.exception)
                }
            }.addOnFailureListener {
                    onRegisterResponse(null, it)
            }
    }
}