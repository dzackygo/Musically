package com.example.musically.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musically.data.MusicallyRepository
import com.example.musically.model.FakeMusically
import com.example.musically.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: MusicallyRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FakeMusically>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FakeMusically>>>
        get() = _uiState

    private val _isRefreshing = mutableStateOf(false)
    val isRefreshing: State<Boolean> = _isRefreshing

    fun getAllMusically() {
        viewModelScope.launch {
            _isRefreshing.value = true
            repository.getAllMusically()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { musicList ->
                    _uiState.value = UiState.Success(musicList)
                    _isRefreshing.value = false
                }
        }
    }

    fun toggleFavorite(id: Long) {
        viewModelScope.launch {
            repository.updateFavoriteMusically(id)
            val currentState = _uiState.value
            if (currentState is UiState.Success) {
                val updatedList = currentState.data.map { music ->
                    if (music.musically.id == id) {
                        music.copy(isFavorite = !music.isFavorite)
                    } else {
                        music
                    }
                }
                _uiState.value = UiState.Success(updatedList)
            }
        }
    }
}