package dz.salim.salimi.e_rem.data.remote

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

object AuthFirebase {

    private val auth = Firebase.auth

    fun registerWithEmail(email: String, password: String): Task<AuthResult> =
        auth.createUserWithEmailAndPassword(email, password)


    fun loginWithEmail(email: String, password: String): Task<AuthResult> =
        auth.signInWithEmailAndPassword(email, password)

    fun loggedInUserUid(): String {
        return auth.currentUser!!.uid
    }
}