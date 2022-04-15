package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.movieapp.models.getMovies
import com.example.movieapp.navigation.MovieNavigation
import com.example.movieapp.screens.home.HomeScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import com.example.movieapp.viewmodel.FavoritesViewModel

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieApp {
                MovieNavigation() // HomeScreen -> DetailScreen()
            }
        }
    }
}

@Composable
fun MovieApp(content: @Composable () -> Unit) {
    MovieAppTheme {
        content()
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MovieApp {

    }
}