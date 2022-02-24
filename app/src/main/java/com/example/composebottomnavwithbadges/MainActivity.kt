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
import com.example.composebottomnavwithbadges.screen.MoreSettingScreen
import com.example.composebottomnavwithbadges.screen.ProfileScreen
import com.example.composebottomnavwithbadges.ui.theme.ComposeBottomNavWithBadgesTheme
import com.example.composebottomnavwithbadges.utils.MenuAction

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomNavWithBadgesTheme {
                //val navController = rememberNavController()
                val appState = rememberAppState()
                Scaffold(
                    topBar = {
                        if (appState.shouldShowBottomAndTopAppBar) {
                            TopAppBarWidget(appState)
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
                            appState.navController,
                            onSnackSelected = appState::navigateToMoreSettings,
                            upPress = appState::upPress
                        )
                    }
                }
            }
        }
    }

}

private fun NavGraphBuilder.navGraph(
    navController: NavHostController,
    onSnackSelected: (String, NavBackStackEntry) -> Unit,
    upPress: () -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.HOME.route
    ) {
        addHomeGraph(navController, onSnackSelected)
    }
    composable(
        "${MainDestinations.HOME_SETTING}/{${MainDestinations.USERID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.USERID_KEY) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val userId = arguments.getString(MainDestinations.USERID_KEY)
        MoreSettingScreen(userId!!, upPress)
    }
    composable(
        "${MainDestinations.HOME_PROFILE}"
        //arguments = listOf(navArgument(MainDestinations.USERID_KEY) { type = NavType.StringType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val userId = arguments.getString(MainDestinations.USERID_KEY)
        ProfileScreen(/*userId!!,*/ upPress)
    }
}


/*
@Composable
fun Navigation(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = "home",
        Modifier.padding(paddingValues)
    ) {
        composable(route = "home") {
            HomeScreen()
        }
        composable(route = "chat") {
            BenefitScreen()
        }
        composable(route = "setting") {
            SettingScreen(navController)
        }
        composable(route = "profile") {
            ProfileScreen(navController)
        }
        composable(route = "more/{selectedItem}") {
            val item = it.arguments?.getString("selectedItem").toString()
            MoreSettingScreen(item, navController)
        }
    }
}
*/

@Composable
private fun TopAppBarWidget(appState: AppState) {
    val navBackStackEntry by appState.navController.currentBackStackEntryAsState()
    TopAppBar(
        title = { Text(text = navBackStackEntry?.destination?.route.toString()/*"TopAppBar Title"*/) },
        navigationIcon = {
            IconButton(onClick = { appState.navigateToProfile(navBackStackEntry!!)/*navigateToDestination(navController, "Profile")*/ }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile Icon")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White,
        elevation = 12.dp,
        actions = {
            IconButton(onClick = { appState.navigateToProfile(navBackStackEntry!!)}) {
                Icon(
                    painter = painterResource(id = MenuAction.Contact.icon),
                    contentDescription = MenuAction.Contact.label.toString()
                )
            }
        }
    )
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}