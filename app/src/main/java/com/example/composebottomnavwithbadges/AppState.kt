package com.example.composebottomnavwithbadges

import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composebottomnavwithbadges.dao.User


/**
 * Destinations used in the App
 */
object MainDestinations {
    const val HOME_ROUTE = "home"
    const val HOME_PROFILE = "profile"
    const val HOME_MORE = "more"
    const val USERID_KEY = "userId"
}

object SecondaryDestinations {
    const val HOME_FAB_ROUTE = "homeFab"
    const val HOME_ITEM_CLICK_PROFILE = "homeItemClick"
    const val HOME_ARG_ID = "homeArgId"
    const val MORE_SETTING_ROUTE = "moreSettings"
}

/**
 * Remembers and creates an instance of app
 */
@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
) =
    remember(scaffoldState, navController) {
        AppState(
            scaffoldState,
            navController
        )
    }

/**
 * Responsible for holding state related to [JetsnackApp] and containing UI-related logic.
 */
@Stable
class AppState(
    val scaffoldState: ScaffoldState,
    val navController: NavHostController,
) {

    val bottomBarTabs = HomeSections.values()
    private val bottomBarRoutes = bottomBarTabs.map { it.route }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomAndTopAppBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

    fun navigateToBottomBarRoute(route: String) {
        if (route != currentRoute) {
            navController.navigate(route) {
                // Pop up backstack to the first destination and save state. This makes going back
                // to the start destination when pressing back in any other bottom tab.
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                // Avoid multiple copies of the same destination when
                // reselecting the same item
                launchSingleTop = true
                // Restore state when reselecting a previously selected item
                restoreState = true
            }
        }
    }

    fun navigateToProfileRoute(route: String, userId: String?) {
        if (route != currentRoute) {
            //navController.navigate("$route/$userId") {
            navController.navigate(route) {
                //Add this code to remove profile screen when navigating back from profile route
//                popUpTo(navController.graph.findNode(MainDestinations.HOME_PROFILE)!!.id) {
//                    saveState = true
//                }
                launchSingleTop = true
                restoreState = false
            }
        }
    }

    fun navigateToMoreRoute(route: String, userId: String?) {
        if (route != currentRoute) {
            navController.navigate(route) {
                /*popUpTo(HomeSections.MORE.route) {
                    saveState = true
                }*/
                launchSingleTop = true
                restoreState = false
            }
        }
    }

    fun navigateToHomeFab(user: User, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        val userId = "Test String"
        if (from.lifecycleIsResumed()) {
            //Currently parcelables are not supported by Navigation try for workaround
            navController.navigate("${SecondaryDestinations.HOME_FAB_ROUTE}/$user")
            //navController.navigate("${SecondaryDestinations.HOME_FAB_ROUTE}/$userId")
        }
    }

    fun navigateToProfile(/*userId: String,*/ from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${MainDestinations.HOME_PROFILE}")
        }
    }

    fun navigateToMoreSettings(userId: String, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            navController.navigate("${SecondaryDestinations.MORE_SETTING_ROUTE}/$userId")
        }
    }

}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)