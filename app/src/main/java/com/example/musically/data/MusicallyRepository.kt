package com.example.musically.data

import com.example.musically.model.FakeMusically
import com.example.musically.model.FakeMusicallyDataSource
import com.example.musically.model.Musically
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class MusicallyRepository {
    private val favoriteMusic = mutableListOf<FakeMusically>()

    init {
        if (favoriteMusic.isEmpty()) {
            FakeMusicallyDataSource.dataDummyMusicfy.forEach { music ->
                favoriteMusic.add(
                    FakeMusically(
                        musically = music,
                        count = 1,
                        isFavorite = false
                    )
                )
            }
        }
    }

    fun getAllMusically(): Flow<List<FakeMusically>> {
        return flowOf(favoriteMusic)
    }

    fun getMusicallyById(musicallyId: Long): FakeMusically {
        return favoriteMusic.first {
            it.musically.id == musicallyId
        }
    }

    fun updateFavoriteMusically(musicallyId: Long) {
        val index = favoriteMusic.indexOfFirst { it.musically.id == musicallyId }
        if (index >= 0) {
            val item = favoriteMusic[index]
            favoriteMusic[index] = item.copy(isFavorite = !item.isFavorite)
        }
    }

    fun getFavoriteMusically(): Flow<List<FakeMusically>> {
        return flowOf(favoriteMusic.filter { it.isFavorite })
    }

    companion object {
        @Volatile
        private var instance: MusicallyRepository? = null

        fun getInstance(): MusicallyRepository =
            instance ?: synchronized(this) {
                instance ?: MusicallyRepository().also { instance = it }
            }
    }
}