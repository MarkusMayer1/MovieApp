package com.example.movieapp.screens.home

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieScreens
import com.example.movieapp.viewmodel.FavoritesViewModel
import com.example.movieapp.widgets.FavoriteIcon
import com.example.movieapp.widgets.MovieRow

@ExperimentalAnimationApi
@Composable
fun HomeScreen(
    navController: NavController = rememberNavController(),
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    var showMenu by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Movies") },
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "more")
                    }

                    DropdownMenu(expanded = showMenu, onDismissRequest = { showMenu = false }) {
                        DropdownMenuItem(onClick = { navController.navigate(route = MovieScreens.FavoritesScreen.name) }) {
                            Row {
                                Icon(
                                    imageVector = Icons.Default.Favorite,
                                    contentDescription = "my favorites",
                                    modifier = Modifier.padding(4.dp)
                                )
                                Text(
                                    text = "Favorites",
                                    modifier = Modifier
                                        .padding(4.dp)
                                        .width(100.dp)
                                )
                            }
                        }
                    }
                })
        }
    ) {
        MainContent(navController = navController, favoritesViewModel = favoritesViewModel)
    }
}

@Composable
fun MainContent(
    navController: NavController,
    movieList: List<Movie> = getMovies(),
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    LazyColumn {
        items(items = movieList) { movie ->
            MovieRow(
                movie = movie,
                onItemClick = { movieId ->
                    navController.navigate(route = MovieScreens.DetailScreen.name + "/$movieId")
                }
            ) {
                FavoriteIcon(
                    movie = movie,
                    isFavorite = favoritesViewModel.isFavorite(movie),
                ) { movie ->
                    favoritesViewModel.toggleFavorite(movie)
                }
            }
        }
    }
}