package com.example.movieapp.screens.detail

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.models.Movie
import com.example.movieapp.models.getMovies
import com.example.movieapp.viewmodel.FavoritesViewModel
import com.example.movieapp.widgets.FavoriteIcon
import com.example.movieapp.widgets.HorizontalScrollableImageView
import com.example.movieapp.widgets.MovieRow
import com.example.movieapp.widgets.SimpleTopAppBar

@ExperimentalAnimationApi
@Composable
fun DetailScreen(
    navController: NavController = rememberNavController(),
    movieId: String? = "tt0499549",
    favoritesViewModel: FavoritesViewModel = viewModel()
) {
    val movie = filterMovie(movieId = movieId)

    Scaffold(
        topBar = {
            SimpleTopAppBar(arrowBackClicked = { navController.popBackStack() }) {
                Text(text = movie.title)
            }
        }
    ) {
        MainContent(movie = movie, favoritesViewModel = favoritesViewModel)
    }
}

@Composable
fun MainContent(movie: Movie, favoritesViewModel: FavoritesViewModel = viewModel()) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column {
            MovieRow(movie = movie) {
                FavoriteIcon(
                    movie = movie,
                    isFavorite = favoritesViewModel.isFavorite(movie),
                ) { movie ->
                    favoritesViewModel.toggleFavorite(movie)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Movie Images",
                style = MaterialTheme.typography.h5,
                textAlign = TextAlign.Center
            )

            HorizontalScrollableImageView(movie = movie)
        }
    }
}

fun filterMovie(movieId: String?): Movie {
    return getMovies().filter { movie -> movie.id == movieId }[0]
}