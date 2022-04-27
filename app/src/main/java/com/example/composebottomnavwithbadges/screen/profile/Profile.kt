package com.example.composebottomnavwithbadges.screen.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.composebottomnavwithbadges.ProfileSections
import com.example.composebottomnavwithbadges.Up


@Composable
fun ProfileScreen(
    onRouteSelected: (String) -> Unit,
    item: String?,
    upPress: () -> Unit
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
            Text(text = "Profile Screen", Modifier.weight(1F), textAlign = TextAlign.Center)
        }
        Text(
            text = "Welcome $item",
            Modifier
                .weight(1F)
                .fillMaxSize()
                .wrapContentHeight(),
            textAlign = TextAlign.Center
        )
        Button(onClick = {
            onRouteSelected(ProfileSections.HOME.route)
        }) {
            Text(text = "Profile")
        }
        Button(onClick = { onRouteSelected(ProfileSections.PERSONALINFO.route) }) {
            Text(text = "Personal Info")
        }
        Button(onClick = { onRouteSelected(ProfileSections.NOTIFICATION.route) }) {
            Text(text = "Notification")
        }
        Button(onClick = { onRouteSelected(ProfileSections.SECURITY.route) }) {
            Text(text = "Security")
        }
    }

}

