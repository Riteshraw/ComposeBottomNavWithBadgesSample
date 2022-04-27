package com.example.composebottomnavwithbadges.screen.home

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebottomnavwithbadges.dao.User

@Composable
fun HomeScreen(
    onFabClick: (User) -> Unit,
    viewModel: HomeViewModel = viewModel()
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Log.v("Compose","HomeScreen Composition")
        Text(text = "HomeScreen")
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
        )
        DisplayList(viewModel,modifier= Modifier.weight(1f))
        //Simple FAB
        DisplayFAB(onFabClick)

    }
}

@Composable
fun DisplayFAB(onFabClick:(User)->Unit) {
    FloatingActionButton(onClick = { onFabClick(User(9999, "Admin")) }) {
        Icon(Icons.Filled.Add, "FAB Home Screen")
    }
}

@Composable
fun DisplayList(viewModel: HomeViewModel,modifier: Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 50.dp)
    ) {
        items(items = viewModel.listResponse) { item ->
            Text(
                text = item,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            )
        }
    }
}
