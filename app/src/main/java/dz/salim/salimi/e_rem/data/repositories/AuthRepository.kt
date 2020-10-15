package dz.salim.salimi.e_rem.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import dz.salim.salimi.e_rem.data.models.user.Login
import dz.salim.salimi.e_rem.data.remote.AuthFirebase
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

    fun registerUser() {
        AuthFirebase.registerWithEmail("", "")
            .addOnCompleteListener {
                if (it.isSuccessful) {

                }
            }.addOnFailureListener {

            }
    }
}