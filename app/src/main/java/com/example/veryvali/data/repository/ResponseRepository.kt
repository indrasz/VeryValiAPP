package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Response
import com.google.firebase.firestore.FirebaseFirestore

class ResponseRepository {
    private val db = FirebaseFirestore.getInstance()
    private val responseCollection = db.collection("responses")
    private val recipientCollection = db.collection("recipients")

    fun createResponseWithRecipientId(
        response: Response,
        recipientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Check if recipient with given ID exists
        recipientCollection.document(recipientId).get()
            .addOnSuccessListener { recipientDocument ->
                if (recipientDocument.exists()) {
                    // Recipient found, create response data
                    responseCollection
                        .add(response.copy(idRecipient = recipientId)) // Set idRecipient of the response
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure(e.message ?: "Failed to create response data.")
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