package com.example.composebottomnavwithbadges.screen.home

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebottomnavwithbadges.MoreSections


@Composable
fun MoreScreen(
    onItemClick: (String) -> Unit,
    onRouteSelected: (String, String) -> Unit,
    viewModel: MoreViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        Log.v("Compose", "More Screen Composition")
        Text(
            text = "MoreScreen",
            Modifier.padding(10.dp)
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(0.dp)
        )

        NavToMoreScreens(onRouteSelected)

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(top = 8.dp)
        ) {
            items(items = viewModel.listResponse) { item ->
                listItem(item = item) { selectedItem ->
                    Toast.makeText(context, "Item clicked : $selectedItem", Toast.LENGTH_SHORT)
                        .show()
                    //navController.navigate("more/$selectedItem")
                    onItemClick(selectedItem)
                }
            }
        }
    }
}

@Composable
fun NavToMoreScreens(onRouteSelected: (String, String) -> Unit) {
    Row(
        Modifier.fillMaxWidth(),
        Arrangement.SpaceEvenly
    ) {
        /*Button(onClick = { onRouteSelected(MoreSections.Home.route, "User_ID") }) {
            Text(text = "Home")
        }*/
        Button(onClick = { onRouteSelected(MoreSections.PERSONALINFO.route, "User_ID") }) {
            Text(text = "Info")
        }
        Button(onClick = { onRouteSelected(MoreSections.SECURITY.route, "User_ID") }) {
            Text(text = "Security")
        }
        Button(onClick = { onRouteSelected(MoreSections.NOTIFICATION.route, "User_ID") }) {
            Text(text = "Notification")
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
