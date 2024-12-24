package com.example.musically.ui.screen.favorite


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.musically.data.MusicallyRepository
import com.example.musically.model.FakeMusically
import com.example.musically.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val repository: MusicallyRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<FakeMusically>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<FakeMusically>>>
        get() = _uiState

    fun getFavoriteMusically() {
        viewModelScope.launch {
            repository.getFavoriteMusically()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { musicList ->
                    _uiState.value = UiState.Success(musicList)
                }
        }
    }

    fun toggleFavorite(id: Long) {
        repository.updateFavoriteMusically(id)
        getFavoriteMusically()
    }
}