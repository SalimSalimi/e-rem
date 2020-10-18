package dz.salim.salimi.e_rem.data.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dz.salim.salimi.e_rem.data.models.Entity
import dz.salim.salimi.e_rem.data.models.content.Content
import dz.salim.salimi.e_rem.data.models.user.User

object DataFirebase {

    private val database = Firebase.database
    val reference = database.reference

    fun <T : Entity> addEntity(entity: T, firebaseRef: String): Task<Void>? {
        return when (entity) {
            is User -> {
                reference.child(firebaseRef).child(entity.id)
                    .setValue(entity)
            }
            is Content -> {
                val newEntity = reference.child(firebaseRef).push()
                entity.id = newEntity.key!!
                newEntity.setValue(entity)
            }
            else -> {
                return null
            }
        }
    }

    fun <T: Entity> updateEntity(entity: T, firebaseRef: String) {
        reference.child(firebaseRef).child(entity.id).setValue(entity)
    }

    fun <T: Entity> deleteEntity(entity: T, firebaseRef: String) {
        reference.child(firebaseRef).child(entity.id).removeValue()
    }

    inline fun <reified T: Entity> getAll(liveData: MutableLiveData<List<T>>, firebaseRef: String) {
        val listData = ArrayList<T>()
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()
                snapshot.children.forEach { entityChild ->
                    val entity = entityChild.getValue(T::class.java)
                    listData.add(entity!!)
                }
                liveData.postValue(listData)
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(listData)
                Log.d("FIREBASE_ERROR", "Can't retrieve data ${error.toException().message}")
            }
        }
        reference.child(
            firebaseRef).addValueEventListener(listener)
    }

    inline fun <reified T: Entity> getAllByFirstChild(
        liveData: MutableLiveData<List<T>>, firebaseRef: String,
        childRef: String, childValue: Any) {

        val listData = ArrayList<T>()
        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                listData.clear()
                snapshot.children.forEach { entityChild ->
                    if (entityChild.child(childRef).value == childValue) {
                        val entity = entityChild.getValue(T::class.java)
                        listData.add(entity!!)
                    }
                }
                liveData.postValue(listData)
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(listData)
                Log.d("FIREBASE_ERROR", "Can't retrieve data ${error.toException().message}")
            }
        }
        reference.child(
            firebaseRef).addValueEventListener(listener)
    }

    inline fun <reified T: Entity> getDataByChild(
        liveData: MutableLiveData<T>, firebaseRef: String,
        childRef: String, childValue: Any) {

        val listener = object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach { entityChild ->
                    Log.d("DataFirebase", "${entityChild.child(childRef).value}")
                    if (entityChild.child(childRef).value == childValue) {
                        val data = entityChild.getValue(T::class.java)
                        liveData.postValue(data)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                liveData.postValue(null)
                Log.d("FIREBASE_ERROR", "Can't retrieve data ${error.toException().message}")
            }
        }
        reference.child(
            firebaseRef).addValueEventListener(listener)
    }

}