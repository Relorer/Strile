package com.example.strile.data_firebase.repositories

import androidx.lifecycle.MutableLiveData
import com.example.strile.data_firebase.models.Executed
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*


class ExecutedRepository: Repository<Executed>() {
    override val referenceName: String = "users/" + FirebaseAuth.getInstance().currentUser?.uid + "/executed"

    override fun getAll(): MutableLiveData<List<Executed>> {
        val liveData: MutableLiveData<List<Executed>> = MutableLiveData()
        reference
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val items: List<Executed> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Executed::class.java)!!.copy(id = dataSnapshot.key!!)
                    }

                    liveData.postValue(items)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Nothing to do
                }
            })
        return liveData
    }

    fun deleteBeforeDate(date: Date) {
        reference.orderByChild("dateComplete").endAt(date.time.toDouble()).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val executed = snapshot.getValue(Executed::class.java)
                    if (executed!!.dateComplete < date.time) {
                        snapshot.ref.removeValue()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })
    }

    fun deleteByCaseId(id: String, typeCase: String?) {

        reference.orderByChild("caseId").equalTo(id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                for (snapshot in dataSnapshot.children) {
                    val executed = snapshot.getValue(Executed::class.java)
                    if (executed?.typeCase.equals(typeCase)) {
                        snapshot.ref.removeValue()
                    }
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
            }
        })

    }
}