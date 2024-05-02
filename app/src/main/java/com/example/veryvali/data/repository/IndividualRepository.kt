package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.Recipient
import com.google.firebase.firestore.FirebaseFirestore

class IndividualRepository {
    private val db = FirebaseFirestore.getInstance()
    private val individualCollection = db.collection("individuals")
    private val recipientCollection = db.collection("recipients")

    fun createIndividualWithRecipientId(
        individual: Individual,
        recipientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Cek apakah penerima dengan ID yang diberikan ada
        recipientCollection.document(recipientId).get()
            .addOnSuccessListener { recipientDocument ->
                if (recipientDocument.exists()) {
                    // Penerima ditemukan, buat data individu
                    individualCollection
                        .add(individual.copy(idRecipient = recipientId)) // Set idRecipient dari individual
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure(e.message ?: "Failed to create individual data.")
                        }
                } else {
                    onFailure("Recipient with ID $recipientId does not exist.")
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to check recipient existence.")
            }
    }
}