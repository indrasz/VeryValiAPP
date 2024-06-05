package com.example.veryvali.di

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.ui.platform.LocalContext
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
        dataPendukung1: Bitmap,
        dataPendukung2: Bitmap,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        _loadingState.value = true

        viewModelScope.launch {
            responseRepository.createResponseWithRecipientId(
                response,
                recipientId,
                dataPendukung1,
                dataPendukung2,
                onSuccess = {
                    _loadingState.value = false
                    onSuccess()
                },
                onFailure = { errorMessage ->
                    _loadingState.value = false
                    onFailure(errorMessage)
                    Log.e("ResponseViewModel", "Failed to create response: $errorMessage")
                }
            )
        }
    }
}