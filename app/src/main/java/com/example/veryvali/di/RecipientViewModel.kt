package com.example.veryvali.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.repository.RecipientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RecipientsViewModel : ViewModel() {
    // Repository untuk berinteraksi dengan sumber daya data
    private val repository = RecipientRepository()

    // MutableStateFlow untuk menyimpan state dari data penerima
    private val _recipientsState = MutableStateFlow<RecipientsState>(RecipientsState.Loading)
    // StateFlow untuk menyediakan akses ke _recipientsState kepada pengguna luar
    val recipientsState: StateFlow<RecipientsState> = _recipientsState

    init {
        // Memuat data penerima saat ViewModel pertama kali dibuat
        getAllRecipients()
    }

    // Fungsi untuk mengambil data penerima dari repository
    private fun getAllRecipients() {
        viewModelScope.launch {
            // Melakukan panggilan ke repository untuk mengambil data penerima
            repository.getAllRecipients(
                // Jika berhasil, mengubah state menjadi Success dan menyertakan data penerima
                onSuccess = { recipients ->
                    _recipientsState.value = RecipientsState.Success(recipients)
                    // Log state data penerima setelah berhasil diambil
//                    Log.d("ScrollContent", "Recipients state view mode: $_recipientsState.value")
                },
                // Jika gagal, mengubah state menjadi Error dan menyertakan pesan kesalahan
                onFailure = { error ->
                    _recipientsState.value = RecipientsState.Error(error)
                }
            )
        }
    }

    // Sealed class yang merepresentasikan state dari data penerima
    sealed class RecipientsState {
        // State saat data sedang dimuat
        data object Loading : RecipientsState()
        // State saat data berhasil diambil, dengan daftar penerima sebagai properti
        data class Success(val recipients: List<Recipient>) : RecipientsState()
        // State saat terjadi kesalahan dalam mengambil data, dengan pesan kesalahan sebagai properti
        data class Error(val message: String) : RecipientsState()
    }
}
