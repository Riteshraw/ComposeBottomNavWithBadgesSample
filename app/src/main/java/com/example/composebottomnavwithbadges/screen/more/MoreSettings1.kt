package com.example.composebottomnavwithbadges.screen.more

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composebottomnavwithbadges.Up

@Composable
fun MoreSetting1Screen(
    item: String,
    upPress: () -> Unit,
    viewModel: MoreSettings1ViewModel = viewModel()
) {
    Column(
        Modifier
            .padding(8.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(Modifier.fillMaxWidth()) {
            Up(upPress)
            Text(text = "More Settings 1 Screen", Modifier.weight(1F), textAlign = TextAlign.Center)
        }
        //display argument text
        Text(
            text = "Argument received : $item",
            Modifier
                .weight(1F)
                .background(Color.White)
                .fillMaxSize()
                .wrapContentHeight(),
            textAlign = TextAlign.Center,
            color = Color.Black
        )
    }
}