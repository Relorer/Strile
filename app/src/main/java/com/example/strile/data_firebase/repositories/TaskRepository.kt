package com.example.strile.data_firebase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.strile.data_firebase.models.Executed
import com.example.strile.data_firebase.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class TaskRepository() : Repository<Task>() {
    override val referenceName: String = "users/" + FirebaseAuth.getInstance().currentUser?.uid + "/tasks"

    private val all = getAll()

    init {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -32)
        deleteBeforeDate(calendar.time)
    }

    override fun getAll(): MutableLiveData<List<Task>> {
        val liveData: MutableLiveData<List<Task>> = MutableLiveData()
        reference
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items: List<Task> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Task::class.java)!!.copy(id = dataSnapshot.key!!)
                    }

                    liveData.postValue(items)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
        return liveData
    }

    fun getById(id: String): LiveData<Task?> {
        return Transformations.map(all) {
            it.stream()
                .filter { m: Task? -> m!!.id == id }
                .findAny()
                .orElse(null)
        }
    }

    private fun deleteBeforeDate(date: Date) {
//        reference.orderByChild("dateComplete").endAt(date.time.toDouble()).addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(dataSnapshot: DataSnapshot) {
//                for (snapshot in dataSnapshot.children) {
//                    val task = snapshot.getValue(Executed::class.java)
//                    if (task!!.dateComplete < date.time) {
//                        snapshot.ref.removeValue()
//                    }
//                }
//            }
//
//            override fun onCancelled(databaseError: DatabaseError) {
//            }
//        })
    }
}