package com.example.composebottomnavwithbadges.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun MoreSettingScreen(item:String,navController: NavHostController) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "More Setting Screen")
        //display argument text
        Text(
            text = "Argument received : $item",
            Modifier
                .weight(1F)
                .background(Color.Cyan)
                .fillMaxSize()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
    }
}
