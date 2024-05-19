package com.example.veryvali.data.repository

import android.graphics.Bitmap
import com.example.veryvali.data.model.Response
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream

class ResponseRepository {
    private val db = FirebaseFirestore.getInstance()
    private val responseCollection = db.collection("responses")
    private val recipientCollection = db.collection("recipients")
    private val storage = FirebaseStorage.getInstance()

    fun createResponseWithRecipientId(
        response: Response,
        recipientId: String,
        dataPendukung1: Bitmap,
        dataPendukung2: Bitmap,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Check if recipient with given ID exists
        recipientCollection.document(recipientId).get()
            .addOnSuccessListener { recipientDocument ->
                if (recipientDocument.exists()) {
                    // Recipient found, upload images
                    uploadImage(dataPendukung1, "data_pendukung1_${System.currentTimeMillis()}") { url1 ->
                        uploadImage(dataPendukung2, "data_pendukung2_${System.currentTimeMillis()}") { url2 ->
                            // Create response data with image URLs
                            val responseWithImages = response.copy(
                                idRecipient = recipientId,
                                dataPendukung1Url = url1,
                                dataPendukung2Url = url2
                            )
                            responseCollection.add(responseWithImages)
                                .addOnSuccessListener { onSuccess() }
                                .addOnFailureListener { e -> onFailure(e.message ?: "Failed to create response data.") }
                        }
                    }
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