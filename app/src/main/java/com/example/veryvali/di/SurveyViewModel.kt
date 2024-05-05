package com.example.veryvali.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.veryvali.data.model.Individual
import com.example.veryvali.data.model.SurveyType
import com.example.veryvali.data.repository.SurveyRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SurveyViewModel : ViewModel() {

    private val surveyRepository = SurveyRepository()

    private val _loadingState = MutableStateFlow(false)
    val loadingState: StateFlow<Boolean> = _loadingState

    fun createSurveyType(
        surveyType: SurveyType,
        recipientId: String,
        onNext: (String) -> Unit
    ) {
        _loadingState.value = true // Set loading state menjadi true saat operasi dimulai

        viewModelScope.launch {
            surveyRepository.createSurveyType(
                surveyType,
                recipientId,
                onSuccess = { documentId ->
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    onNext(documentId)
                },
                onFailure = {
                    _loadingState.value = false // Set loading state menjadi false saat operasi selesai
                    // Handle failure if needed
                }
            )
        }
    }
}