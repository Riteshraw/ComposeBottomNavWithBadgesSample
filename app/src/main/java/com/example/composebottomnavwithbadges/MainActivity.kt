package com.example.composebottomnavwithbadges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.composebottomnavwithbadges.dao.User
import com.example.composebottomnavwithbadges.screen.home.HomeFabScreen
import com.example.composebottomnavwithbadges.screen.more.MoreSettingScreen
import com.example.composebottomnavwithbadges.ui.theme.ComposeBottomNavWithBadgesTheme
import com.example.composebottomnavwithbadges.utils.MenuAction

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomNavWithBadgesTheme {
                val appState = rememberAppState()
                Scaffold(
                    topBar = {
                        if (appState.shouldShowBottomAndTopAppBar) {
                            TopAppBarWidget(
                                appState,
                                navigateToRoute = { route, userId ->
                                    appState.navigateToProfileRoute(
                                        route,
                                        userId
                                    )
                                }
                            )
                        }
                    },
                    bottomBar = {
                        if (appState.shouldShowBottomAndTopAppBar) {
                            BottomNavigationBar(
                                tabs = appState.bottomBarTabs,
                                currentRoute = appState.currentRoute!!,
                                navController = appState.navController,
                                navigateToRoute = { route -> appState.navigateToBottomBarRoute(route) }
                            )
                        }
                    }) { innerPaddingModifier ->
                    NavHost(
                        navController = appState.navController,
                        startDestination = MainDestinations.HOME_ROUTE,
                        modifier = Modifier.padding(innerPaddingModifier)
                    ) {
                        navGraph(
                            onProfileItemSelected = appState::navigateToProfileRoute,
                            onItemSelected = appState::navigateToMoreSettings,
                            onMoreRouteSelected = appState::navigateToMoreRoute,
                            onFabSelected = appState::navigateToHomeFab,
                            upPress = appState::upPress
                        )
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.navGraph(
    onProfileItemSelected: (String, String?) -> Unit,
    onMoreRouteSelected: (String, String?) -> Unit,
    onItemSelected: (String, NavBackStackEntry) -> Unit,
    onFabSelected: (User, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.HOME.route
    ) {
        addHomeGraph(onItemSelected, onFabSelected, onMoreRouteSelected)
    }
    navigation(
        route = MainDestinations.HOME_PROFILE,
        startDestination = ProfileSections.HOME.route
    ) {
        addProfileGraph(
            onProfileItemSelected, "Test_user", upPress
        )
    }
    navigation(
        route = MainDestinations.HOME_MORE,
        startDestination = MoreSections.PERSONALINFO.route
    ) {
        addMoreGraph(
            onMoreRouteSelected, "Test_user", upPress
        )
    }
    composable(
        "${SecondaryDestinations.MORE_SETTING_ROUTE}/{${MainDestinations.USERID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.USERID_KEY) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val userId = arguments.getString(MainDestinations.USERID_KEY)
        MoreSettingScreen(
            userId!!,
            upPress
        )
    }
    composable(
        "${SecondaryDestinations.HOME_FAB_ROUTE}/{${SecondaryDestinations.HOME_ARG_ID}}",
        //arguments = listOf(navArgument(SecondaryDestinations.HOME_ARG_ID) { type = NavType.StringType }
        arguments = listOf(navArgument(SecondaryDestinations.HOME_ARG_ID) {
            type = NavType.ParcelableType(User::class.java)
            defaultValue = User()
        }
        )
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val argsId = arguments.getParcelable<User>(SecondaryDestinations.HOME_ARG_ID)
        HomeFabScreen(argsId!!, upPress)
    }
}

@Composable
private fun TopAppBarWidget(appState: AppState, navigateToRoute: (String, String) -> Unit) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    TopAppBar(
        title = { Text(text = navBackStackEntry?.destination?.route.toString()/*"TopAppBar Title"*/) },
        navigationIcon = {
            IconButton(onClick = { navigateToRoute(MainDestinations.HOME_PROFILE, "Test_User2") }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile Icon")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White,
        elevation = 12.dp,
        actions = {
            IconButton(onClick = { appState.navigateToProfile(navBackStackEntry!!) }) {
                Icon(
                    painter = painterResource(id = MenuAction.Contact.icon),
                    contentDescription = MenuAction.Contact.label.toString()
                )
            }
        }
    )
}