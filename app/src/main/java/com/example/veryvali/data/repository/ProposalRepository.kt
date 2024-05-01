package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.SurveyType
import com.google.firebase.firestore.FirebaseFirestore

class ProposalRepository {

    private val db = FirebaseFirestore.getInstance()
    private val proposalCollection = db.collection("proposals")

    fun createProposal(proposal: Proposal, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        proposalCollection
            .add(proposal)
            .addOnSuccessListener {
                onSuccess()
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to create proposal data.")
            }
    }
}