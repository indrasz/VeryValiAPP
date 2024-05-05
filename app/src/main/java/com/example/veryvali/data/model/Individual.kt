package com.example.veryvali.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Individual(
    val noKK: String,
    val nik: String,
    val namaLengkap: String,
    val ibuKandung: String,
    val jenisPekerjaan: String,
    val tanggalLahir: String,
    val tempatLahir: String,
    val jenisKelamin: String,
    val statusPerkawinan: String,
    val hubunganKeluarga: String,
    val pendidikanTerakhir: String,
    val alamat: String,
    val kecamatan: String,
    val kelurahan: String,
    val lingkungan: String,
    val idRecipient: String,
//    val id: String
) : Parcelable
