package com.example.strile.data_firebase.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.example.strile.data_firebase.models.Executed
import com.example.strile.data_firebase.models.Habit
import com.example.strile.data_firebase.models.User
import com.example.strile.infrastructure.progress.Progress
import com.example.strile.utilities.extensions.DateUtilities
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*

class HabitRepository : Repository<Habit>() {
    override val referenceName: String = "users/" + FirebaseAuth.getInstance().currentUser?.uid + "/habits"

    private val all = getAll()
    private val executed = ExecutedRepository().getAll()

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

    fun updateExecuted(habit: Habit, newValue: Boolean) {
        var ex: Executed? = null
        val _executed = executed.value
        val today = DateUtilities.getDateOfDayWithoutTime(Date()).time
        if (_executed != null) {
            for (item in _executed) {
                if (item.typeCase == Habit::class.java.canonicalName && item.caseId == habit.id && item.dateComplete >= today) {
                    ex = item
                }
            }
        }

        var exp = 0

        val day = Date()
        if (newValue) {
            if (ex == null) {
                exp = (habit.streakByDay(day) + 1) * (habit.difficulty + 1) / 2
                val executed = Executed(
                    "", habit.id, habit.name, Date().time, exp,
                    Habit::class.java.canonicalName
                )
                ExecutedRepository().update(executed)
            }
        } else {
            exp = -1 * (habit.streakByDay(day) + 2) * (habit.difficulty + 1) / 2
            ExecutedRepository().deleteByCaseId(habit.id, Habit::class.java.canonicalName)
        }

        UserRepository().getCurrentUser { u: User? ->
            UserRepository().updateWithoutLists(Progress.addExperience(exp, u))
        }
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