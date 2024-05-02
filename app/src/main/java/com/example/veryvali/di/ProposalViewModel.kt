package com.example.veryvali.di

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.Proposal
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.repository.IndividualRepository
import com.example.veryvali.data.repository.ProposalRepository
import com.example.veryvali.data.repository.RecipientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProposalViewModel : ViewModel() {
    private val repository = RecipientRepository()
    private val proposalRepository = ProposalRepository()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun cekDataNIK(
        nik: String,
        onNext: (Recipient) -> Unit
    ) {
        _loadingState.value = true // Set isLoading true saat proses pengecekan dimulai
        repository.checkNIK(
            nik,
            onSuccess = { recipient ->
//                Log.d("Recipient Data from view model","$recipient")
                _loadingState.value = false // Set isLoading false saat proses selesai
                onNext(recipient)
            },
            onFailure = {
                _loadingState.value = false // Set isLoading false saat proses selesai
                // Handle failure jika NIK tidak ditemukan
            }
        )
    }

    fun createProposal(
        proposal: Proposal,
        recipientId: String,
        onNext: (Proposal) -> Unit
    ) {
        _loadingState.value = true // Set loading state menjadi true saat operasi dimulai

        viewModelScope.launch {
            proposalRepository.createProposalWithRecipientId(
                proposal,
                recipientId,
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