package com.example.veryvali.data.repository

import android.util.Log
import com.example.veryvali.data.model.Recipient
import com.google.firebase.firestore.FirebaseFirestore
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecipientRepository {
    private val firestore = FirebaseFirestore.getInstance()

    // Fungsi untuk mengecek data NIK pada Firebase
    fun checkNIK(nik: String, onSuccess: (Recipient) -> Unit, onFailure: () -> Unit) {
        val recipientsCollection = firestore.collection("recipients")

        recipientsCollection.whereEqualTo("NIK", nik).get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    // Data dengan NIK yang sesuai ditemukan
                    val document = documents.first()
//                    val recipient = Recipient(
//                        alamat = document.getString("ALAMAT") ?: "",
//                        bansos = document.getString("BANSOS") ?: "",
//                        desa = document.getString("DESA") ?: "",
////                        idBansos = document.getString("ID BANSOS") ?: "",
//                        kabupaten = document.getString("KABUPATEN") ?: "",
//                        kecamatan = document.getString("KECAMATAN") ?: "",
//                        nama = document.getString("NAMA") ?: "",
//                        nik = document.getString("NIK") ?: "",
//                        statusDTKS = document.getString("STATUS DTKS") ?: "",
//                        tanggalLahir = document.getString("TANGGAL LAHIR") ?: "",
//                        umur = document.getString("UMUR") ?: "",
//                        id = document.id,
//                    )
//                    Log.d("Recipient Data from repository", "$recipient")
//                    onSuccess(recipient)
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

    // Fungsi untuk mengambil seluruh data penerima
    fun getAllRecipients(
        onSuccess: (List<Recipient>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val recipientsList = mutableListOf<Recipient>()

        firestore.collection("recipients")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val alamat = document.getString("ALAMAT") ?: ""
                    val bansos = document.getString("BANSOS") ?: ""
                    val desa = document.getString("DESA") ?: ""
                    val idBansos = document.getString("ID BANSOS") ?: ""
                    val kabupaten = document.getString("KABUPATEN") ?: ""
                    val kecamatan = document.getString("KECAMATAN") ?: ""
                    val nama = document.getString("NAMA") ?: ""
                    val nik = document.getString("NIK") ?: ""
                    val statusDTKS = document.getString("STATUS DTKS") ?: ""
                    val tanggalLahirString = document.getString("TANGGAL LAHIR") ?: ""
                    val umur = document.getString("UMUR") ?: ""
                    val id = document.id

                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                    val recipient = Recipient(
//                        alamat = alamat,
//                        bansos = bansos,
//                        desa = desa,
////                        idBansos = idBansos,
//                        kabupaten = kabupaten,
//                        kecamatan = kecamatan,
//                        nama = nama,
//                        nik = nik,
//                        statusDTKS = statusDTKS,
//                        tanggalLahir = tanggalLahirString,
//                        umur = umur,
//                        id = id
//                    )
//                    recipientsList.add(recipient)
                }
                onSuccess(recipientsList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error occurred.")
            }
    }
}