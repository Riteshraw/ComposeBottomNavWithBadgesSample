package com.example.composebottomnavwithbadges

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.composebottomnavwithbadges.screen.*
import com.example.composebottomnavwithbadges.ui.theme.ComposeBottomNavWithBadgesTheme
import com.example.composebottomnavwithbadges.utils.MenuAction

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomNavWithBadgesTheme {
                val navController = rememberNavController()
                val a = 1
                val b = 2
                val result = if(a == 1 || a== 2)  b else a
                    Scaffold(
                        topBar = {
                            //TopAppBarWidget(navController)
                            if (currentRoute(navController) != "profile" /*||
                            currentRoute(navController) != "More/{selectedItem}"*/
                            ) {
                                TopAppBarWidget(navController)
                            }
                        },
                        bottomBar = {
                            if (currentRoute(navController) != "profile" /*||
                            currentRoute(navController) != "More/{selectedItem}"*/
                            ) {
                                BottomNavigationBar(
                                    items = itemList,
                                    navController = navController,
                                    onItemClick = {
                                        navigateToDestination(navController, it.route)
                                    }
                                )
                            }
                        }) {
                        Navigation(navController = navController, paddingValues = it)
                    }
            }
        }
    }

}

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
            ChatScreen()
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

@Composable
private fun TopAppBarWidget(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    TopAppBar(
        title = { Text(text = navBackStackEntry?.destination?.route.toString()/*"TopAppBar Title"*/) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("Profile")/*navigateToDestination(navController, "Profile")*/ }) {
                Icon(imageVector = Icons.Filled.Person, contentDescription = "Profile Icon")
            }
        },
        backgroundColor = colorResource(id = R.color.purple_200),
        contentColor = Color.White,
        elevation = 12.dp,
        actions = {
            IconButton(onClick = { /*navigateToDestination(navController, "profile") */ }) {
                Icon(
                    painter = painterResource(id = MenuAction.Contact.icon),
                    contentDescription = MenuAction.Contact.label.toString()
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.LightGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = Color.Red,
                unselectedContentColor = Color.White,
                /*modifier = modifier.padding(4.dp).background(Color.Cyan),*/
                icon = {
                    Column(horizontalAlignment = CenterHorizontally) {
                        if (item.badgeCount > 0) {
                            BadgeBox(badgeContent = {
                                Text(text = item.badgeCount.toString())
                            }) {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.name
                                )
                            }
                        } else {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.name
                            )
                        }
                        if (selected) {
                            Text(
                                text = item.name,
                                textAlign = TextAlign.Center,
                                fontSize = 10.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

fun navigateToDestination(navController: NavHostController, destination: String) {
    navController.navigate(destination) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
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

@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    //navController.graph.displayName
    return navBackStackEntry?.destination?.route
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeBottomNavWithBadgesTheme {
        Greeting("Android")
    }
}