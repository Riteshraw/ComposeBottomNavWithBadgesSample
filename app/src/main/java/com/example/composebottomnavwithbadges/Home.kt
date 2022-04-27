package com.example.composebottomnavwithbadges

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
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
import com.example.composebottomnavwithbadges.dao.User
import com.example.composebottomnavwithbadges.screen.home.*
import com.example.composebottomnavwithbadges.screen.more.MoreSetting1Screen
import com.example.composebottomnavwithbadges.screen.more.MoreSettingScreen

fun NavGraphBuilder.addHomeGraph(
    onSnackSelected: (String, NavBackStackEntry) -> Unit,
    onFabSelected: (User, NavBackStackEntry) -> Unit,
    onMoreRouteSelected: (String,String) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.HOME.route) { from ->
        HomeScreen(onFabClick = { user -> onFabSelected(user, from) })
    }
    composable(HomeSections.BENEFITS.route) { from ->
        BenefitScreen()
    }
    composable(HomeSections.SAVINGS.route) { from ->
        SavingScreen()
    }
    composable(HomeSections.MORE.route) { from ->
        MoreScreen(
            onItemClick = { id -> onSnackSelected(id, from) },
            onRouteSelected = { route, args -> onMoreRouteSelected(route, args) }
        )
    }
}

fun NavGraphBuilder.addProfileGraph(
    onProfileItemSelected: (String, String?) -> Unit,
    arguments: String,
    upPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    composable(ProfileSections.HOME.route) { from ->
        ProfileScreen(
            onRouteSelected = { route -> onProfileItemSelected(route, arguments) },
            arguments,
            upPress
        )
    }
    composable(ProfileSections.PERSONALINFO.route) { from ->
        PersonalInfoScreen()
    }
    composable(ProfileSections.NOTIFICATION.route) { from ->
        NotificationScreen()
    }
    composable(ProfileSections.SECURITY.route) { from ->
        SecurityScreen()
    }
}

fun NavGraphBuilder.addMoreGraph(
    onMoreRouteSelected: (String, String?) -> Unit,
    arguments: String,
    upPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    /*composable(HomeSections.MORE.route) { from ->
        MoreScreen(
            onRouteSelected = { route, args -> onMoreRouteSelected(route, args) },
            onItemClick = { }
        )
    }*/
    composable(MoreSections.PERSONALINFO.route) { from ->
        MoreSettingScreen(arguments, upPress)
    }
    composable(MoreSections.SECURITY.route) { from ->
        MoreSetting1Screen(arguments, upPress)
    }
    composable(MoreSections.NOTIFICATION.route) { from ->
        MoreSetting1Screen(arguments, upPress)
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val iconSelected: ImageVector,
    val route: String
) {
    HOME(R.string.home_home, Icons.Filled.Home, Icons.Outlined.Home, "home/home"),
    BENEFITS(R.string.home_benefits, Icons.Filled.Search, Icons.Outlined.Search, "home/benefits"),
    SAVINGS(R.string.home_savings,Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart,"home/savings"),
    MORE(R.string.home_more, Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle, "home/more")
}

enum class ProfileSections(
    @StringRes val title: Int,
    val route: String
) {
    HOME(R.string.profile_home, "profile/home"),
    PERSONALINFO(R.string.profile_info, "profile/info"),
    SECURITY(R.string.profile_security, "profile/security"),
    NOTIFICATION(R.string.profile_notification, "profile/notification")
}

enum class MoreSections(
    @StringRes val title: Int,
    val route: String
) {
    HOME(R.string.setting_home, "more/home"),
    PERSONALINFO(R.string.setting_info, "more/info"),
    SECURITY(R.string.setting_security, "more/security"),
    NOTIFICATION(R.string.setting_notification, "more/notification")
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

@Composable
fun Up(upPress: () -> Unit) {
    IconButton(
        onClick = upPress,
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 0.dp)
            .size(24.dp)

    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = "Back Navigation"
        )
    }
}
