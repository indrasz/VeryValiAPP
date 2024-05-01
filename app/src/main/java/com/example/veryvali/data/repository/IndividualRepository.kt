package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Individual
import com.google.firebase.firestore.FirebaseFirestore

class IndividualRepository {

    private val db = FirebaseFirestore.getInstance()
    private val individualCollection = db.collection("individuals")

    fun createIndividual(individual: Individual, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        individualCollection
            .add(individual)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to create individual data.")
            }
    }

}