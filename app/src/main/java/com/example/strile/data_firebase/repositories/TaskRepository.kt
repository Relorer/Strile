package com.example.strile.data_firebase.repositories

import androidx.lifecycle.MutableLiveData
import com.example.strile.data_firebase.models.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class TaskRepository {
    private val database = FirebaseDatabase.getInstance("https://strile-default-rtdb.europe-west1.firebasedatabase.app/")
    private val tasksReference = database.getReference("tasks")

    fun fetchTasks(liveData: MutableLiveData<List<Task>>) {
        tasksReference
            .orderByChild("name")
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val newsFeedItems: List<Task> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Task::class.java)!!.copy(id = dataSnapshot.key!!)
                    }

                    liveData.postValue(newsFeedItems)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
    }

    fun updateTask(task: Task) {
        task.id = if (task.id.isNullOrBlank()) tasksReference.push().key.toString() else task.id
        tasksReference.child(task.id!!).setValue(task)
    }

}