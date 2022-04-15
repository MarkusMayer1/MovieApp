package com.example.movieapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.screens.detail.DetailScreen
import com.example.movieapp.screens.favourites.FavoritesScreen
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.viewmodel.FavoritesViewModel

@ExperimentalAnimationApi
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    val favoritesViewModel: FavoritesViewModel = viewModel()

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                favoritesViewModel = favoritesViewModel
            )
        }

        // url: www.domain.com/detailscreen/movie=12
        composable(
            MovieScreens.DetailScreen.name + "/{movieId}",
            arguments = listOf(navArgument("movieId") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId"),
                favoritesViewModel = favoritesViewModel
            )
        }

        composable(MovieScreens.FavoritesScreen.name) {
            FavoritesScreen(
                navController = navController,
                favoritesViewModel = favoritesViewModel
            )
        }

        // add more routes and screens here
    }
}