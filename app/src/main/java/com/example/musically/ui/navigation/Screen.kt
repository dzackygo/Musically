package com.example.musically.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object Favorite : Screen ("favorite")
    object Detail : Screen("detail/{musicallyId}") {
        fun createRoute(musicallyId: Long) = "detail/$musicallyId"
    }
}