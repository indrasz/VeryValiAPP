package com.example.veryvali.di
import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.remote.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BansosViewModel : ViewModel() {

    // MutableStateFlow untuk menyimpan state dari data bansos
    private val _recipientsState = MutableStateFlow<BansosState>(BansosState.Loading)
    // StateFlow untuk menyediakan akses ke _recipientsState kepada pengguna luar
    val recipientsState: StateFlow<BansosState> = _recipientsState

    init {
        // Memuat data bansos saat ViewModel pertama kali dibuat
        fetchRecipients()
    }

    // Fungsi untuk mengambil data bansos dari API
    private fun fetchRecipients() {
        viewModelScope.launch {
            try {
                Log.d("BansosViewModel", "Fetching recipients...")
                val response = RetrofitInstance.api.getAllRecipients()
                Log.d("BansosViewModel", "Recipients fetched successfully: $response")
                _recipientsState.value = BansosState.Success(response)
            } catch (e: Exception) {
                Log.d("BansosViewModel", "Error fetching recipients: ${e.message}")
                _recipientsState.value = BansosState.Error(e.message ?: "Unknown error occurred.")
            }
        }
    }

    // Sealed class yang merepresentasikan state dari data bansos
    sealed class BansosState {
        // State saat data sedang dimuat
        object Loading : BansosState()
        // State saat data berhasil diambil, dengan daftar penerima sebagai properti
        data class Success(val recipients: List<Recipient>) : BansosState()
        // State saat terjadi kesalahan dalam mengambil data, dengan pesan kesalahan sebagai properti
        data class Error(val message: String) : BansosState()
    }
}