package com.example.veryvali.data.remote

import android.util.Log
import com.example.veryvali.data.model.Recipient
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

interface BansosApiService {
    @GET("api.php?limit=25&page=1")
    suspend fun getAllRecipients(): List<Recipient>

    @GET("api.php")
    suspend fun getRecipients(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): List<Recipient>

    @GET("api-search.php")
    suspend fun searchByNIK(
        @Query("nik") nik: String
    ): List<Recipient>

    @GET("api-search.php")
    suspend fun checkNIK(
        @Query("nik") nik: String
    ): List<Recipient>
}

object RetrofitInstance {
    private const val BASE_URL = "https://verivali.ecodify.id/public/"
//    private const val BASE_URL = "http://192.168.2.36:8080/verivali-admin/"

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
    private val apiService = RetrofitInstance.api
    suspend fun fetchRecipients(limit: Int, page: Int, onSuccess: (List<Recipient>) -> Unit, onFailure: (String) -> Unit) {
        try {
            val recipients = RetrofitInstance.api.getRecipients(limit, page)
            recipients.forEach { recipient ->
                val status = checkAndMarkRecipient(recipient)
                recipient.status = status // Add status to the recipient
            }
            onSuccess(recipients)
        } catch (e: Exception) {
            onFailure(e.message ?: "Unknown error occurred.")
        }
    }


    suspend fun searchByNIK(
        nik: String,
        onSuccess: (List<Recipient>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        try {
            val response = RetrofitInstance.api.searchByNIK(nik)
            if (response.isNotEmpty()) {
                onSuccess(response)
            } else {
                onFailure("NIK tidak ditemukan.")
            }
        } catch (e: Exception) {
            onFailure(e.message ?: "Unknown error occurred.")
        }
    }
    suspend fun checkAndMarkRecipient(recipient: Recipient): String {
        return try {
            val responsesCollection = firestore.collection("responses")
            val querySnapshot = responsesCollection
                .whereEqualTo("idRecipient", recipient.nik)
                .get()
                .await()

            if (!querySnapshot.isEmpty) {
                "Sudah ditanggapi"
            } else {
                "Belum ditanggapi"
            }
        } catch (e: Exception) {
            Log.d("BansosRepository", "Error fetching document: ${e.message}")
            "error"
        }
    }


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
