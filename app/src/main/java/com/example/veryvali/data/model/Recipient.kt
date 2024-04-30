package com.example.veryvali.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.Date

@Parcelize
data class Recipient(
    val alamat: String,
    val bansos: String,
    val desa: String,
    val idBansos: String,
    val kabupaten: String,
    val kecamatan: String,
    val nama: String,
    val nik: String,
    val statusDTKS: String,
    val tanggalLahir: Date,
    val umur: String
) : Parcelable
