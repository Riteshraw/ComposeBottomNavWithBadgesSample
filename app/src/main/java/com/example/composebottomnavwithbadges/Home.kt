package com.example.composebottomnavwithbadges

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composebottomnavwithbadges.screen.BenefitScreen
import com.example.composebottomnavwithbadges.screen.HomeScreen
import com.example.composebottomnavwithbadges.screen.SettingScreen

fun NavGraphBuilder.addHomeGraph(
    navController: NavHostController,
    onSnackSelected: (String, NavBackStackEntry) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.HOME.route) { from ->
        HomeScreen()
    }
    composable(HomeSections.BENEFITS.route) { from ->
        BenefitScreen()
    }
    composable(HomeSections.SAVINGS.route) { from ->
        BenefitScreen()
    }
    composable(HomeSections.MORE.route) { from ->
        SettingScreen(onItemClick = { id -> onSnackSelected(id, from) })
    }

    /*composable(ProfileSection.Home.route) {
        ProfileScreen(navController)
    }
    composable(SettingSection.Home.route"more/{selectedItem}") {
        val item = it.arguments?.getString("selectedItem").toString()
        MoreSettingScreen(item, navController)
    }*/
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val route: String
) {
    HOME(R.string.home_home, Icons.Filled.Home, Icons.Outlined.Home, "home/home"),
    BENEFITS(R.string.home_benefits, Icons.Filled.Search, Icons.Outlined.Search, "home/benefits"),
    SAVINGS(
        R.string.home_savings,
        Icons.Filled.ShoppingCart,
        Icons.Outlined.ShoppingCart,
        "home/savings"
    ),
    MORE(R.string.home_more, Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, "home/more")
}

enum class ProfileSections(
    @StringRes val title: Int,
    val route: String
) {
    Home(R.string.profile_home, "profile/home"),
    PersonalInfo(R.string.profile_info, "profile/info"),
    Security(R.string.profile_security, "profile/security"),
    Notification(R.string.profile_notification, "profile/notification")
}

enum class SettingSections(
    @StringRes val title: Int,
    val route: String
) {
    Home(R.string.setting_home, "setting/home"),
    PersonalInfo(R.string.setting_info, "setting/info"),
    Security(R.string.setting_security, "setting/security"),
    Notification(R.string.setting_notification, "setting/notification")
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavigationBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navController: NavController,
    modifier: Modifier = Modifier,
    navigateToRoute: (String) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    val routes = remember { tabs.map { it.route } }
    val currentSection = tabs.first { it.route == currentRoute }

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.LightGray,
        elevation = 5.dp
    ) {
        tabs.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { navigateToRoute(item.route) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White,
                /*modifier = modifier.padding(4.dp).background(Color.Cyan),*/
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        if (selected) {
                            Icon(
                                imageVector = item.iconSelected,
                                contentDescription = item.name
                            )
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = Color.Red
                            )
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp,
                                color = Color.White
                            )

                        }
                    }
                }
            )
        }
    }
}
