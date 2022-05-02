package com.example.movieapp.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTopAppBar(arrowBackClicked: () -> Unit = {}, content: @Composable () -> Unit) {
    TopAppBar(backgroundColor = MaterialTheme.colors.primaryVariant, elevation = 3.dp) {
        Row {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "arrow back",
                modifier = Modifier.clickable {
                    arrowBackClicked()    // go back to last screen
                })

            Spacer(modifier = Modifier.width(20.dp))

            content()
        }
    }
}