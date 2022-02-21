package com.example.composebottomnavwithbadges

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
    val badgeCount: Int = 0
)

val itemList = listOf<BottomNavItem>(
    BottomNavItem("Home", "home", Icons.Default.Home),
    BottomNavItem("Chat", "chat", Icons.Default.Notifications, 12),
    BottomNavItem("Setting", "setting", Icons.Default.Settings),
)
