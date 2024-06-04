package com.example.veryvali.data.repository

import android.content.Context
import android.util.Log
import com.example.veryvali.data.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class AuthRepository {
    // Fungsi untuk melakukan registrasi akun pengguna
    fun registerUser(user: User, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        // Autentikasi dengan Firebase Authentication
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.email, user.password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val firebaseUser = FirebaseAuth.getInstance().currentUser
                    if (firebaseUser != null) {
                        // Jika autentikasi berhasil, simpan data pengguna ke Firestore
                        val firestore = FirebaseFirestore.getInstance()
                        val userCollection = firestore.collection("users")
                        userCollection.document(firebaseUser.uid).set(user)
                            .addOnSuccessListener {
                                // Registrasi berhasil
                                onSuccess()
                            }
                            .addOnFailureListener { e ->
                                // Gagal menyimpan data pengguna
                                onFailure(e.message ?: "Unknown error occurred.")
                            }
                    } else {
                        // Gagal mendapatkan instance pengguna dari autentikasi Firebase
                        onFailure("Failed to get user instance.")
                    }
                } else {
                    // Gagal melakukan autentikasi
                    onFailure(authTask.exception?.message ?: "Unknown error occurred.")
                }
            }
    }

    //fungsi untuk login
    fun userLogin(
        context: Context,
        email: String,
        password: String,
        onSuccess: (User) -> Unit,
        onFailure: (String) -> Unit
    ) {
        // Authenticate with Firebase Authentication
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    val user = FirebaseAuth.getInstance().currentUser
                    if (user != null) {
                        val userId = user.uid
                        val db = FirebaseFirestore.getInstance()
                        val userDocRef = db.collection("users").document(userId)
                        userDocRef.get().addOnCompleteListener { docTask ->
                            if (docTask.isSuccessful) {
                                val document = docTask.result
                                if (document.exists()) {
                                    try {
                                        val userData = document.toObject(User::class.java)?.copy(userId = userId)
                                        Log.d("User data in Firestore", userData.toString())
                                        if (userData != null) {
                                            saveUserDataToPreferences(context, userData)
                                            onSuccess(userData)
                                        } else {
                                            onFailure("User data is null")
                                        }
                                    } catch (e: Exception) {
                                        Log.e("User data error", "Error parsing user data", e)
                                        onFailure("Error parsing user data")
                                    }
                                } else {
                                    Log.d("Firestore", "No user data found")
                                    onFailure("No user data found")
                                }
                            } else {
                                Log.e("Firestore", "Failed to fetch user data", docTask.exception)
                                onFailure(docTask.exception?.message ?: "Failed to fetch user data")
                            }
                        }
                    } else {
                        Log.e("Auth", "User is null after successful login")
                        onFailure("Failed to get user information")
                    }
                } else {
                    Log.e("Auth", "Login failed", authTask.exception)
                    onFailure(authTask.exception?.message ?: "Unknown error occurred.")
                }
            }
    }

    //fungsi untuk logout
    fun userLogout(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        FirebaseAuth.getInstance().signOut()
        // Check if sign out was successful
        if (FirebaseAuth.getInstance().currentUser == null) {
            onSuccess()
        } else {
            onFailure("Failed to log out.")
        }
    }

    //untuk nyimpana data user ketika login
    fun saveUserDataToPreferences(context: Context, user: User) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString("userId", user.userId)
            putString("username", user.username)
            putString("nip", user.nip)
            putString("email", user.email)
            putString("whatsappNumber", user.whatsappNumber)
            putString("password", user.password)
            apply()
        }
    }

    //mengambil data user pada saat login
    fun getUserDataFromPreferences(context: Context): User? {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getString("userId", null)
        val username = sharedPreferences.getString("username", null)
        val nip = sharedPreferences.getString("nip", null)
        val email = sharedPreferences.getString("email", null)
        val whatsappNumber = sharedPreferences.getString("whatsappNumber", null)
        val password = sharedPreferences.getString("password", null)

        return if (userId != null && username != null && nip != null && email != null && whatsappNumber != null && password != null) {
            User(userId, username, nip, email, whatsappNumber, password)
        } else {
            null
        }
    }

    //hapus data user login
    fun clearUserDataFromPreferences(context: Context) {
        val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
}