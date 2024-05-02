package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.SurveyType
import com.google.firebase.firestore.FirebaseFirestore

class SurveyRepository {

    private val db = FirebaseFirestore.getInstance()
    private val surveyCollection = db.collection("surveys")
    private val recipientCollection = db.collection("recipients")

    fun createSurveyType(
        surveyType: SurveyType,
        recipientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        recipientCollection.document(recipientId).get()
            .addOnSuccessListener { recipientDocument ->
                if (recipientDocument.exists()) {
                    // Penerima ditemukan, buat data individu
                    surveyCollection
                        .add(surveyType.copy(idRecipient = recipientId)) // Set idRecipient dari individual
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