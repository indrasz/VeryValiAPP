package com.example.veryvali.data.repository

import com.example.veryvali.data.model.User
import com.google.firebase.auth.FirebaseAuth
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

    // Fungsi untuk melakukan login dengan NIP dan password
    fun userLogin(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        // Autentikasi dengan Firebase Authentication
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { authTask ->
                if (authTask.isSuccessful) {
                    // Login berhasil
                    onSuccess()
                } else {
                    // Login gagal
                    onFailure(authTask.exception?.message ?: "Unknown error occurred.")
                }
            }
    }

//    fun logout(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
//        // Lakukan logout dari Firebase Authentication
//        FirebaseAuth.getInstance().signOut()
//            .addOnCompleteListener { logoutTask ->
//                if (logoutTask.isSuccessful) {
//                    // Logout berhasil
//                    onSuccess()
//                } else {
//                    // Logout gagal
//                    onFailure(logoutTask.exception?.message ?: "Unknown error occurred.")
//                }
//            }
//    }
}