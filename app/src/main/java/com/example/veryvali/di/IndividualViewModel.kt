package com.example.veryvali.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.Recipient
import com.example.veryvali.data.repository.IndividualRepository
import com.example.veryvali.data.repository.RecipientRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class IndividualViewModel : ViewModel() {

    private val individualRepository = IndividualRepository()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun createIndividualWithRecipientId(
        individual: Individual,
        recipientId: String,
        onNext: (String) -> Unit,
//        onFailure: (String) -> Unit
    ) {
        _loadingState.value = true // Set loading state menjadi true saat operasi dimulai

        viewModelScope.launch {
            individualRepository.createIndividualWithRecipientId(
                individual,
                recipientId,
                onSuccess = { documentId ->
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    onNext(documentId)
                },
                onFailure = { e ->
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    // Handle failure if needed
//                    onFailure(e)
                }
            )
        }
    }
}