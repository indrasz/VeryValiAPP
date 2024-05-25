package com.example.veryvali.data.remote

import com.example.veryvali.data.model.Recipient
import com.google.firebase.firestore.FirebaseFirestore
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface BansosApiService {
    @GET("api.php?limit=25&page=1")
    suspend fun getAllRecipients(): List<Recipient>

    @GET("api.php?limit=25&page=1")
    suspend fun checkNIK(
        @Query("nik") nik: String
    ): List<Recipient>
}

object RetrofitInstance {
    private const val BASE_URL = "https://verivali.ecodify.id/public/"

    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BansosApiService by lazy {
        retrofit.create(BansosApiService::class.java)
    }
}

class BansosRepository {
    private val firestore = FirebaseFirestore.getInstance()

    suspend fun checkNIK(
        nik: String,
        onSuccess: (Recipient) -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            val response = RetrofitInstance.api.checkNIK(nik)
            val recipient = response.find { it.nik == nik }
            if (recipient != null) {
                saveRecipientToFirestore(recipient, onSuccess, onFailure)
            } else {
                onFailure("NIK tidak ditemukan.")
            }
        } catch (e: Exception) {
            onFailure(e.message ?: "Unknown error occurred.")
        }
    }

    private fun saveRecipientToFirestore(
        recipient: Recipient,
        onSuccess: (Recipient) -> Unit,
        onFailure: (String) -> Unit
    ) {
        val docRef = firestore.collection("recipients").document(recipient.nik)

        docRef.get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    // Document with the same NIK already exists
                    onSuccess(recipient)
                } else {
                    // Document does not exist, so add the new recipient
                    docRef.set(recipient)
                        .addOnSuccessListener {
                            onSuccess(recipient)
                        }
                        .addOnFailureListener { e ->
                            onFailure(e.message ?: "Error saving to Firestore.")
                        }
                }
            }
            .addOnFailureListener { e ->
                onFailure(e.message ?: "Error checking Firestore.")
            }
    }
}