package com.example.strile.data_firebase.repositories

import androidx.lifecycle.MutableLiveData
import com.example.strile.data_firebase.models.IModel
import com.google.firebase.database.FirebaseDatabase

abstract class Repository<Model: IModel> {
    protected abstract val referenceName: String

    private val database = FirebaseDatabase.getInstance("https://strile-default-rtdb.europe-west1.firebasedatabase.app/")
    protected val reference by lazy { database.getReference(referenceName) }

    abstract fun getAll() : MutableLiveData<List<Model>>

    open fun update(model: Model) {
        model.id = model.id.ifBlank { reference.push().key.toString() }
        reference.child(model.id).setValue(model)
    }

    fun delete(model: Model) {
        reference.child(model.id).removeValue()
    }

}