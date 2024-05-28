package com.example.veryvali.data.repository

import android.util.Log
import com.example.veryvali.data.model.Recipient
import com.google.firebase.firestore.FirebaseFirestore

class RecipientRepository {
    private val firestore = FirebaseFirestore.getInstance()

    // Fungsi untuk mengambil seluruh data penerima
    fun getAllRecipients(
        onSuccess: (List<Recipient>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val recipientsList = mutableListOf<Recipient>()

        firestore.collection("recipients")
            .get()
            .addOnSuccessListener {
                onSuccess(recipientsList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error occurred.")
            }
    }
}