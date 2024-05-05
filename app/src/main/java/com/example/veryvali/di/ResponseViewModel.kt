package com.example.veryvali.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Response
import com.example.veryvali.data.repository.ResponseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ResponseViewModel : ViewModel() {

    private val responseRepository = ResponseRepository()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun createResponseWithRecipientId(
        response: Response,
        recipientId: String,
    ) {
        _loadingState.value = true // Set loading state menjadi true saat operasi dimulai

        viewModelScope.launch {
            responseRepository.createResponseWithRecipientId(
                response,
                recipientId,
                onSuccess = {
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
//                    onNext()
                },
                onFailure = {errorMessage ->
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    // Handle failure if needed
                    Log.e("ResponseViewModel", "Failed to create response: $errorMessage")
                }
            )
        }
    }
}