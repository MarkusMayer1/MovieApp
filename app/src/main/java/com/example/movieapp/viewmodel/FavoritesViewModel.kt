package com.example.movieapp.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.movieapp.models.Movie

class FavoritesViewModel : ViewModel() {
    private val _favoriteMovies = mutableStateListOf<Movie>()

    fun getFavoriteMovies(): List<Movie> {
        return _favoriteMovies
    }

    fun addToFavorites(movie: Movie) {
        if (!isFavorite(movie = movie)) {
            _favoriteMovies.add(movie)
        }
    }

    fun removeFromFavorites(movie: Movie) {
        _favoriteMovies.remove(movie)
    }

    fun isFavorite(movie: Movie): Boolean {
        return _favoriteMovies.contains(movie)
    }

    fun toggleFavorite(movie: Movie) {
        if (isFavorite(movie = movie)) {
            removeFromFavorites(movie = movie)
        } else {
            addToFavorites(movie = movie)
        }
    }
}