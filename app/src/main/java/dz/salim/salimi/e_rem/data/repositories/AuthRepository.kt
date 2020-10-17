package dz.salim.salimi.e_rem.data.repositories

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import dz.salim.salimi.e_rem.data.models.user.Login
import dz.salim.salimi.e_rem.data.models.user.Teacher
import dz.salim.salimi.e_rem.data.remote.AuthFirebase
import dz.salim.salimi.e_rem.data.remote.DataFirebase
import dz.salim.salimi.e_rem.utils.TEACHER_REF
import java.lang.Exception

object AuthRepository {

    val LOGGED_IN_USER_UID = AuthFirebase.loggedInUserUid()

    fun loginUser(login: Login, onLoginResponse: ((Boolean?, Exception?) -> Unit)) {
        AuthFirebase.loginWithEmail(login.email, login.password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    onLoginResponse(true, null)
                } else {
                    onLoginResponse(false, null)
                }
                Log.d("AuthRepo", "${it.exception?.message}")
            }.addOnFailureListener {
                    onLoginResponse(null, it)
            }
    }

    fun registerUser(teacher: Teacher, login: Login, onRegisterResponse: ((Boolean?, Exception?) -> Unit)) {
        AuthFirebase.registerWithEmail(login.email, login.password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val uid = FirebaseAuth.getInstance().currentUser!!.uid
                    teacher.id = uid
                    DataFirebase.addEntity(teacher, TEACHER_REF)?.addOnCompleteListener {
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