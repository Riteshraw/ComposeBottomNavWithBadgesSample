package com.example.composebottomnavwithbadges.screen

import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController


@Composable
fun SettingScreen(
    navController: NavHostController,
    viewModel: SettingViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Text(text = "SettingScreen")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        )
        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = viewModel.listResponse) { item ->
                listItem(item = item) { selectedItem ->
                    Toast.makeText(context, "Item clicked : $selectedItem", Toast.LENGTH_SHORT)
                        .show()
                    navController.navigate("More/{$selectedItem}")
                }
            }
        }
    }
}

@Composable
fun listItem(item: String, onClick: (String) -> Unit) {
    Text(
        text = item,
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clickable { onClick(item) }
    )
}
