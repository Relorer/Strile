package com.example.strile.data_firebase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.strile.data_firebase.models.User
import com.example.strile.infrastructure.progress.Progress
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class UserRepository {
    private val referenceName: String = "users"
    private val database = FirebaseDatabase.getInstance("https://strile-default-rtdb.europe-west1.firebasedatabase.app/")
    private val reference by lazy { database.getReference(referenceName) }

    fun update() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        reference.child(uid).child("id").setValue(uid)
    }

    fun mergeWithCurrent(user: User?) {
        getCurrentUser {
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            if (it != null && user != null) {
                user.executed.putAll(it.executed)
                user.tasks.putAll(it.tasks)
                user.habits.putAll(it.habits)
                user.id = uid
                user.dateLastActiveDay = it.dateLastActiveDay
                user.experience = it.experience
                user.goalExperience = it.goalExperience
                user.level = it.level
            }
            reference.child(uid).setValue(user)
        }
    }

    fun updateWithoutLists(user: User?) {
        if (user != null) {
            val uid = FirebaseAuth.getInstance().currentUser!!.uid
            reference.child(uid).child("dateLastActiveDay").setValue(user.dateLastActiveDay)
            reference.child(uid).child("experience").setValue(user.experience)
            reference.child(uid).child("goalExperience").setValue(user.goalExperience)
            reference.child(uid).child("level").setValue(user.level)
        }
    }

    fun deleteCurrentUser() {
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            val uid = user.uid
            reference.child(uid).removeValue()
        }
    }

    fun getCurrentUser(result: (User?) -> Unit) {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        reference.child(uid).get().addOnCompleteListener() {
            if (it.isSuccessful) {
                result.invoke(it.result.getValue(User::class.java))
            }
        }
    }

    fun getCurrentUser(): LiveData<User> {
        val liveData: MutableLiveData<User> = MutableLiveData()
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        reference.child(uid)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user: User? = snapshot.getValue(User::class.java)

                    liveData.postValue(user)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
        return liveData
    }

}