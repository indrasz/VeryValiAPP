package com.example.veryvali.data.model


import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SurveyType(
    val question1: String,
    val question2: String,
    val question3: String,
    val question4: String,
    val question5: String,
    val question6: String,
    val question7: String,
    val question8: String,
    val question9: String,
    val question10: String,
    val idRecipient: String,
) : Parcelable
