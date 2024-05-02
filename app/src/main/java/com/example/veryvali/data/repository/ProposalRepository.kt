package com.example.veryvali.data.repository

import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.SurveyType
import com.google.firebase.firestore.FirebaseFirestore

class ProposalRepository {

    private val db = FirebaseFirestore.getInstance()
    private val proposalCollection = db.collection("proposals")
    private val recipientCollection = db.collection("recipients")

    fun createProposalWithRecipientId(
        proposal: Proposal,
        recipientId: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        recipientCollection.document(recipientId).get()
            .addOnSuccessListener { recipientDocument ->
                if (recipientDocument.exists()) {
                    // Penerima ditemukan, buat data individu
                    proposalCollection
                        .add(proposal.copy(idRecipient = recipientId)) // Set idRecipient dari individual
                        .addOnSuccessListener {
                            onSuccess()
                        }
                        .addOnFailureListener { e ->
                            onFailure(e.message ?: "Failed to create proposal data.")
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