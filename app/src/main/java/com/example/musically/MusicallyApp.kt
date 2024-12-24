package com.example.musically

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.musically.di.Injection
import com.example.musically.model.Musically
import com.example.musically.ui.ViewModelFactory
import com.example.musically.ui.navigation.Screen
import com.example.musically.ui.screen.detail.DetailScreen
import com.example.musically.ui.screen.favorite.FavoriteScreen
import com.example.musically.ui.screen.home.HomeScreen
import com.example.musically.ui.screen.home.HomeViewModel
import com.example.musically.ui.screen.profile.ProfileScreen
import com.example.musically.ui.theme.MusicallyTheme

@Composable
fun MusicallyApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),

) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navController,
                navigateToDetail = { musicallyId ->
                    navController.navigate(Screen.Detail.createRoute(musicallyId))
                }
            )
        }
        composable(Screen.Favorite.route) {
            FavoriteScreen(navController) {
            }
        }
        composable(Screen.Profile.route) {
            ProfileScreen(navController)
        }
        composable(
            Screen.Detail.route,
            arguments = listOf(navArgument("musicallyId") { type = NavType.LongType}),
        ) {
            val id = it.arguments?.getLong("musicallyId") ?: -1L
            DetailScreen(
                musicallyId = id,
                navController = navController,
            )
        }
    }
}

@Composable
@Preview(showBackground = true, device = Devices.PIXEL_4)
@Preview(showBackground = true, device = Devices.PIXEL_4, uiMode = UI_MODE_NIGHT_YES)
fun MusicallyItemPreview() {
    MusicallyTheme {
        MusicallyApp()
    }
}