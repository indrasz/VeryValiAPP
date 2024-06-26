package com.example.veryvali.di

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.remote.BansosRepository
import com.example.veryvali.data.repository.ProposalRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProposalViewModel : ViewModel() {
    private val repository = BansosRepository()
    private val proposalRepository = ProposalRepository()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun cekDataNIK(
        nik: String,
        onNext: (Recipient) -> Unit,
//        onFailure: (String) -> Unit,
    ) {
        _loadingState.value = true // Set isLoading true saat proses pengecekan dimulai
        viewModelScope.launch {
            repository.searchCheckByNIK(
                nik,
                onSuccess = { recipient ->
                    Log.d("ProposalViewModel", "Recipient Data: $recipient")
                    _loadingState.value = false // Set isLoading false saat proses selesai
                    onNext(recipient)
//                    function(recipient)
                },
                onFailure = { error ->
                    Log.d("ProposalViewModel", "Error: $error")
                    _loadingState.value = false // Set isLoading false saat proses selesai
//                    onFailure(error)
                }
            )
        }
    }

    fun createNIK(
        nik: String,
//        onFailure: (String) -> Unit
    ) {
        _loadingState.value = true // Set isLoading true saat proses pengecekan dimulai
        viewModelScope.launch {
            repository.checkNIK(
                nik,
                onSuccess = { recipient ->
                    Log.d("ProposalViewModel", "Recipient Data: $recipient")
                    _loadingState.value = false // Set isLoading false saat proses selesai
                },
                onFailure = { error ->
                    Log.d("ProposalViewModel", "Error: $error")
                    _loadingState.value = false // Set isLoading false saat proses selesai
//                    onFailure(error)
                }
            )
        }
    }

    fun createProposal(
        proposal: Proposal,
        recipientId: String,
        fotoKTP: Bitmap,
        fotoRumah: Bitmap,
        onNext: (Proposal) -> Unit
    ) {
        _loadingState.value = true // Set loading state menjadi true saat operasi dimulai

        viewModelScope.launch {
            proposalRepository.createProposalWithRecipientId(
                proposal,
                recipientId,
                fotoKTP,
                fotoRumah,
                onSuccess = {
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    onNext(proposal)
                },
                onFailure = {
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    // Handle failure if needed
                }
            )
        }
    }
}