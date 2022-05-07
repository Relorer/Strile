package com.example.strile.data_firebase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.strile.data_firebase.models.Habit
import com.example.strile.data_firebase.models.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class HabitRepository : Repository<Habit>() {
    override val referenceName: String = "users/" + FirebaseAuth.getInstance().currentUser?.uid + "/habits"

    private val all = getAll()

    override fun getAll(): MutableLiveData<List<Habit>> {
        val liveData: MutableLiveData<List<Habit>> = MutableLiveData()
        reference
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items: List<Habit> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Habit::class.java)!!.copy(id = dataSnapshot.key!!)
                    }

                    liveData.postValue(items)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
        return liveData
    }

    fun getById(id: String): LiveData<Habit?> {
        return Transformations.map(all) {
            it.stream()
                .filter { m: Habit? -> m!!.id == id }
                .findAny()
                .orElse(null)
        }
    }
}