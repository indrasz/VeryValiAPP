package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.SurveyType
import com.google.firebase.firestore.FirebaseFirestore

class SurveyRepository {

    private val db = FirebaseFirestore.getInstance()
    private val individualCollection = db.collection("surveys")

    fun createSurveyType(surveyType: SurveyType, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        individualCollection
            .add(surveyType)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to create survey data.")
            }
    }
}