package com.example.veryvali.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Recipient(
    val id: String,
    val nama: String,
    val nik: String,
    val tanggal_lahir: String,
    val umur: String,
    val bansos: String,
    val status_dtks: String,
    val alamat: String,
    val kabupaten: String,
    val kecamatan: String,
    val desa: String
) : Parcelable
