package com.example.movieapp.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph
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
    val navController = rememberNavController() // create NavController instance
    val favoritesViewModel: FavoritesViewModel = viewModel() // create ViewModel instance

    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) { // pass the navController instance
        composable(route = MovieScreens.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                favoritesViewModel = favoritesViewModel
            )
        }

        // url: www.domain.com/detailscreen/movie=12
        composable(
            route = MovieScreens.DetailScreen.name + "/{movieId}", // placeholder for arguments
            arguments = listOf(navArgument("movieId") { // define arguments that can be passed
                type = NavType.StringType
            })
        ) { backStackEntry ->
            DetailScreen(
                navController = navController,
                movieId = backStackEntry.arguments?.getString("movieId"), // pass the value of movieId argument to the DetailScreen composable
                favoritesViewModel = favoritesViewModel
            )
        }

        composable(route = MovieScreens.FavoritesScreen.name) {
            FavoritesScreen(
                navController = navController,
                favoritesViewModel = favoritesViewModel
            )
        }

        // routes lead to the corresponding composable function
        // add more routes and screens here
    }
}