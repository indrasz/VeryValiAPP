package com.example.veryvali.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userId: String = "",
    val username: String = "",
    val nip: String = "",
    val email: String = "",
    val whatsappNumber: String = "",
    val password: String = "",
) : Parcelable
