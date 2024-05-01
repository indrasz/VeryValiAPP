package com.example.veryvali.data.repository

import androidx.compose.runtime.Composable
import com.example.veryvali.data.model.Recipient
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RecipientRepository {
    private val firestore = FirebaseFirestore.getInstance()

    // Fungsi untuk mengecek data NIK pada Firebase
    fun checkNIK(NIK: String, onSuccess: () -> Unit, onFailure: () -> Unit) {
        val recipientsCollection = firestore.collection("recipients")

        recipientsCollection.whereEqualTo("NIK", NIK).get()
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

                    // Parse tanggal lahir dari string ke tipe Date
                    val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
//                    val tanggalLahir = try {
//                        dateFormat.parse(tanggalLahirString)
//                    } catch (e: ParseException) {
//                        Date()
//                    }
                    val recipient = Recipient(
                        alamat = alamat,
                        bansos = bansos,
                        desa = desa,
                        idBansos = idBansos,
                        kabupaten = kabupaten,
                        kecamatan = kecamatan,
                        nama = nama,
                        nik = nik,
                        statusDTKS = statusDTKS,
                        tanggalLahir = tanggalLahirString,
                        umur = umur
                    )
                    recipientsList.add(recipient)
                }
                onSuccess(recipientsList)
            }
            .addOnFailureListener { exception ->
                onFailure(exception.message ?: "Unknown error occurred.")
            }
    }
}