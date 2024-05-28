package com.example.veryvali.data.repository

import android.graphics.Bitmap
import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.SurveyType
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ProposalRepository {

    private val db = FirebaseFirestore.getInstance()
    private val proposalCollection = db.collection("proposals")
    private val recipientCollection = db.collection("recipients")
    private val storage = FirebaseStorage.getInstance()

    fun createProposalWithRecipientId(
        proposal: Proposal,
        recipientId: String,
        fotoKTP: Bitmap,
        fotoRumah: Bitmap,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        recipientCollection.document(recipientId).get()
            .addOnSuccessListener { recipientDocument ->
                if (recipientDocument.exists()) {
                    uploadImage(fotoKTP, "foto_ktp_${System.currentTimeMillis()}") { url1 ->
                        uploadImage(fotoRumah, "foto_rumah_${System.currentTimeMillis()}") { url2 ->
                            // Create response data with image URLs
                            val proposalWithImages = proposal.copy(
                                idRecipient = recipientId,
                                fotoKTP = url1,
                                fotoRumah = url2
                            )
                            proposalCollection.add(proposalWithImages)
                                .addOnSuccessListener { onSuccess() }
                                .addOnFailureListener { e -> onFailure(e.message ?: "Failed to create response data.") }
                        }
                    }
                    // Penerima ditemukan, buat data individu

                } else {
                    onFailure("Recipient with ID $recipientId does not exist.")
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Failed to check recipient existence.")
            }
    }

    private fun resizeBitmap(bitmap: Bitmap, maxSize: Int): Bitmap {
        val width = bitmap.width
        val height = bitmap.height

        val bitmapRatio = width.toFloat() / height.toFloat()
        return if (bitmapRatio > 1) {
            val scaledWidth = maxSize
            val scaledHeight = (scaledWidth / bitmapRatio).toInt()
            Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true)
        } else {
            val scaledHeight = maxSize
            val scaledWidth = (scaledHeight * bitmapRatio).toInt()
            Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, true)
        }
    }

    private fun uploadImage(bitmap: Bitmap, name: String, onComplete: (String) -> Unit) {
        val storageRef = storage.reference.child("images/$name.jpg")
        val baos = ByteArrayOutputStream()
        var quality = 100
        var data: ByteArray
        var resizedBitmap = resizeBitmap(bitmap, 1024) // Resize to max dimension of 1024

        do {
            baos.reset()
            resizedBitmap.compress(Bitmap.CompressFormat.JPEG, quality, baos)
            data = baos.toByteArray()
            quality -= 5
        } while (data.size > 1_000_000 && quality > 0)

        val uploadTask = storageRef.putBytes(data)
        uploadTask.addOnFailureListener { exception ->
            onComplete("")
        }.addOnSuccessListener { taskSnapshot ->
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                onComplete(uri.toString())
            }
        }
    }
}