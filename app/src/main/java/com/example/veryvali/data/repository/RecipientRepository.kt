package com.example.veryvali.data.repository

import com.google.firebase.firestore.FirebaseFirestore

class RecipientRepository {
    private val firestore = FirebaseFirestore.getInstance()

    // Fungsi untuk mengecek data NIK pada Firebase
    fun checkNIK(nik: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val recipientsCollection = firestore.collection("recipients")

        recipientsCollection.whereEqualTo("NIK", nik).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // Data dengan NIK yang sesuai ditemukan
                    onSuccess()
                } else {
                    // Data dengan NIK yang sesuai tidak ditemukan
                    onFailure()
                }
            }
            .addOnFailureListener {
                // Gagal mengambil data dari Firestore
                onFailure()
            }
    }
}