package com.app.omdbmovieapp.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.app.omdbmovieapp.presentation.viewmodel.MoviesViewModel

@Composable
fun MovieRegistrationScreen(
    navController: NavController,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var imdbRating by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 30.dp)
    ) {
        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = director,
            onValueChange = { director = it },
            label = { Text("Director") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = genre,
            onValueChange = { genre = it },
            label = { Text("Genre") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Year") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = imdbRating,
            onValueChange = { imdbRating = it },
            label = { Text("IMDb Rating") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotEmpty() && director.isNotEmpty() && genre.isNotEmpty() && year.isNotEmpty() && imdbRating.isNotEmpty()) {
                    viewModel.registerMovie(
                        title,
                        director,
                        genre,
                        year,
                        imdbRating
                    )
                    navController.popBackStack()
                }

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Register Movie")
        }
    }
}