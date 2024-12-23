package com.example.musically.di

import com.example.musically.data.MusicallyRepository

object Injection {
    fun provideRepository(): MusicallyRepository {
        return MusicallyRepository.getInstance()
    }
}