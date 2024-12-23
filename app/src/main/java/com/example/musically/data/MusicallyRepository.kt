package com.example.musically.data

class MusicallyRepository {


    companion object {
        @Volatile
        private var instance: MusicallyRepository? = null

        fun getInstance(): MusicallyRepository =
            instance ?: synchronized(this) {
                MusicallyRepository().apply {
                    instance = this
                }
            }
    }
}