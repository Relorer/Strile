package com.example.strile.data_firebase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.strile.data_firebase.models.Executed
import com.example.strile.data_firebase.models.Task
import com.example.strile.data_firebase.models.User
import com.example.strile.infrastructure.progress.Progress
import com.example.strile.utilities.extensions.DateUtilities
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class TaskRepository : Repository<Task>() {
    override val referenceName: String =
        "users/" + FirebaseAuth.getInstance().currentUser?.uid + "/tasks"

    private val all = getAll()
    private val executed = ExecutedRepository().getAll()

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

    fun updateExecuted(task: Task, newValue: Boolean) {
        var ex: Executed? = null
        val _executed = executed.value
        val today = DateUtilities.getDateOfDayWithoutTime(Date()).time
        if (_executed != null) {
            for (item in _executed) {
                if (item.typeCase == Task::class.java.canonicalName && item.caseId == task.id && item.dateComplete >= today) {
                    ex = item
                }
            }
        }

        var exp = 0

        if (newValue) {
            if (ex == null) {
                exp = 10 * (task.difficulty + 1)
                val executed = Executed(
                    "", task.id, task.name, Date().time, exp,
                    Task::class.java.canonicalName
                )
                ExecutedRepository().update(executed)
            }
        } else {
            exp = -10 * (task.difficulty + 1)
            ExecutedRepository().deleteByCaseId(
                task.id,
                Task::class.java.canonicalName
            )
        }

        UserRepository().getCurrentUser { u: User? ->
            UserRepository().updateWithoutLists(Progress.addExperience(exp, u))
        }
    }

    private fun deleteBeforeDate(date: Date) {
        reference.orderByChild("dateComplete").endAt(date.time.toDouble())
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    for (snapshot in dataSnapshot.children) {
                        val task = snapshot.getValue(Executed::class.java)
                        if (task!!.dateComplete != 0L && task!!.dateComplete < date.time) {
                            snapshot.ref.removeValue()
                        }
                    }
                }

                override fun onCancelled(databaseError: DatabaseError) {
                }
            })
    }
}