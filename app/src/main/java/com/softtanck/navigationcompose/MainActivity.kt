package com.softtanck.navigationcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.softtanck.navigationcompose.route.CustomNavigationRoute
import com.softtanck.navigationcompose.route.navigation.CustomNavigationItem
import com.softtanck.navigationcompose.ui.theme.BOTTOM_COLOR700
import com.softtanck.navigationcompose.ui.theme.Purple700
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navigationController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            val scope = rememberCoroutineScope()
            // Set the drawer position.
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navigationController) },
                    drawerGesturesEnabled = true,
                    drawerContent = {
                        Text(text = "Drawer Content", Modifier.clickable {
                            scope.launch { scaffoldState.drawerState.close() }
//                            LaunchedEffect(scaffoldState) {
//                                scaffoldState.drawerState.close()
//                            }
                        })
                    },
                    drawerElevation = 9.dp,
                    drawerShape = RoundedCornerShape(8.dp),
                    drawerScrimColor = Purple700.copy(0.4f),
                    scaffoldState = scaffoldState
                ) {

                }
                CustomNavigationRoute.instance.InstallNavigationCore(navigationController)
            }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavController) {
        val items = listOf(
            CustomNavigationItem.MyScreen,
            CustomNavigationItem.HomeScreen
        )
        BottomNavigation(
            backgroundColor = BOTTOM_COLOR700,
            contentColor = Color.White,
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(id = item.icon), contentDescription = item.title, Modifier.height(30.dp)) },
                    label = { Text(text = item.title) },
                    selectedContentColor = Color.White,
                    unselectedContentColor = Color.White.copy(0.4f),
                    alwaysShowLabel = true,
                    selected = currentRoute == item.route,
                    onClick = {
                        navController.navigate(item.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            navController.graph.startDestinationRoute?.let { route ->
                                popUpTo(route) {
                                    saveState = true
                                }
                            }
                            // Avoid multiple copies of the same destination when
                            // reselecting the same item
                            launchSingleTop = true
                            // Restore state when reselecting a previously selected item
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}