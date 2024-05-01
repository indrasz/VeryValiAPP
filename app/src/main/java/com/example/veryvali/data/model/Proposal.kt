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
) : Parcelable
