package com.example.musically.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musically.data.MusicallyRepository
import com.example.musically.model.FakeMusically
import com.example.musically.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(
    private val repository: MusicallyRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<FakeMusically>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<FakeMusically>>
        get() = _uiState

    fun getMusicallyById(musicallyId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getMusicallyById(musicallyId))
        }
    }
}