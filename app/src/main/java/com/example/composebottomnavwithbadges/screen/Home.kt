package com.example.composebottomnavwithbadges.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "HomeScreen")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Text(text = viewModel.valResponse.toString())
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        Button(onClick = { viewModel.updateVal() }) {
            Text(text = "Update Val")
        }
    }
}
