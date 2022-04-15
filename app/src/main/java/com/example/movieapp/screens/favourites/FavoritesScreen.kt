package com.example.movieapp.screens.favourites

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.viewmodel.FavoritesViewModel
import com.example.movieapp.widgets.MovieRow

@ExperimentalAnimationApi
@Composable
fun FavoritesScreen(
    navController: NavController = rememberNavController(),
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = MaterialTheme.colors.primaryVariant, elevation = 3.dp) {
                Row {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "arrow back",
                        modifier = Modifier.clickable {
                            navController.popBackStack()    // go back to last screen
                        })

                    Spacer(modifier = Modifier.width(20.dp))

                    Text(text = "My Favorite Movies")
                }
            }
        }
    ) {
        MainContent(navController = navController, favoritesViewModel = favoritesViewModel)
    }
}

@ExperimentalAnimationApi
@Composable
fun MainContent(
    navController: NavController,
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LazyColumn {
            items(items = favoritesViewModel.getFavoriteMovies()) { movie ->
                MovieRow(
                    movie = movie,
                    onItemClick = { movieId ->
                        navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                    }
                )
            }
        }
    }
}