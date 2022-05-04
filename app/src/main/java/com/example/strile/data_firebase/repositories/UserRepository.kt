package com.example.strile.data_firebase.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class UserRepository {
    private val referenceName: String = "Users"
    private val database = FirebaseDatabase.getInstance("https://strile-default-rtdb.europe-west1.firebasedatabase.app/")
    private val reference by lazy { database.getReference(referenceName) }

    fun update() {
        val uid = FirebaseAuth.getInstance().currentUser!!.uid
        reference.child(uid).child("uid").setValue(uid)
    }
}