package com.example.veryvali.di

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.veryvali.data.model.User
import com.example.veryvali.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentSnapshot

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val _userData = MutableLiveData<User?>()
    val userData: LiveData<User?> = _userData

    private var authRepository = AuthRepository()

    init {
        loadUserDataFromPreferences()
    }

    private fun loadUserDataFromPreferences() {
        val context = getApplication<Application>().applicationContext
        val user = authRepository.getUserDataFromPreferences(context)
        _userData.value = user
    }

    fun setUserData(user: User) {
        _userData.value = user
        val context = getApplication<Application>().applicationContext
        authRepository.saveUserDataToPreferences(context, user)
    }

    fun clearUserData() {
        _userData.value = null
        val context = getApplication<Application>().applicationContext
        authRepository.clearUserDataFromPreferences(context)
    }
}