package com.example.veryvali.di
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.model.Response
import com.example.veryvali.data.remote.BansosRepository
import com.example.veryvali.data.remote.RetrofitInstance
import com.example.veryvali.data.repository.ResponseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BansosViewModel : ViewModel() {

    private val repository = BansosRepository()
    private var currentPage = 1
    private val limit = 25
    private val _recipients = mutableListOf<Recipient>()
    private val _recipientsState = MutableStateFlow<BansosState>(BansosState.Loading)
    val recipientsState: StateFlow<BansosState> = _recipientsState
    var isLoadingMore = false // Add a flag for loading more data

    init {
        fetchRecipients()
    }

    fun fetchRecipients() {
        if (isLoadingMore) return // Prevent multiple loadings at the same time
        isLoadingMore = true
        viewModelScope.launch {
            try {
                repository.fetchRecipients(
                    limit = limit,
                    page = currentPage,
                    onSuccess = { recipients ->
                        if (recipients.isNotEmpty()) {
                            _recipients.addAll(recipients)
                            _recipientsState.value = BansosState.Success(_recipients.toList())
                            currentPage++
                        } else if (_recipients.isEmpty()) {
                            _recipientsState.value = BansosState.Error("No recipients found.")
                        }
                    },
                    onFailure = { error ->
                        _recipientsState.value = BansosState.Error(error)
                    }
                )
            } catch (e: Exception) {
                _recipientsState.value = BansosState.Error(e.message ?: "Unknown error occurred.")
            } finally {
                isLoadingMore = false // Reset the flag after loading
            }
        }
    }

    fun searchRecipientByNIK(nik: String) {
        viewModelScope.launch {
            try {
                repository.searchByNIK(
                    nik = nik,
                    onSuccess = { recipients ->
                        _recipientsState.value = BansosState.Success(recipients)
                    },
                    onFailure = { error ->
                        _recipientsState.value = BansosState.Error(error)
                    }
                )
            } catch (e: Exception) {
                _recipientsState.value = BansosState.Error(e.message ?: "Unknown error occurred.")
            }
        }
    }


    fun resetPage() {
        currentPage = 1
        _recipients.clear()
    }

    sealed class BansosState {
        object Loading : BansosState()
        data class Success(val recipients: List<Recipient>) : BansosState()
        data class Error(val message: String) : BansosState()
    }
}