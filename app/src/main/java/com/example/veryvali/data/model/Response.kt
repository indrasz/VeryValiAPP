package com.example.veryvali.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Response(
    val statusKelayakan: Boolean,
    val alasan: String,
    val catatan: String,
    val idRecipient: String,
    val dataPendukung1Url: String? = null,
    val dataPendukung2Url: String? = null,
    val idUser: String
) : Parcelable