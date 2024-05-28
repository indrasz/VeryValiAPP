package com.example.veryvali.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Proposal(
    val programBansos: String,
    val disabilitas: String,
    val tanggalHamil: String,
    val statusOrangTua: String,
    val mapsLatitude: String,
    val mapsLongitude: String,
    val fotoKTP: String? = null,
    val fotoRumah: String? = null,
    val idRecipient: String,
    val idIndividual: String,
    val idSurvey: String,
    val idUser: String
) : Parcelable
