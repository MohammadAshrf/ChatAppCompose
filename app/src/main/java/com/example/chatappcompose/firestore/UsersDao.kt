package com.example.chatappcompose.firestore

import com.example.chatappcompose.firestore.model.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

object UsersDao {
    private fun getUsersCollection(): CollectionReference {
        val database = Firebase.firestore
        return database.collection("users")
    }

    fun createUser(user: User, onCompleteListener: OnCompleteListener<Void>) {
        val docRef = getUsersCollection()
            .document(user.id ?: "")
        docRef.set(user)
            .addOnCompleteListener(onCompleteListener)
    }
}