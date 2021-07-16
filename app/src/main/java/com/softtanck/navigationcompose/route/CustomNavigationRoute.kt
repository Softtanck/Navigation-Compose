package com.softtanck.navigationcompose.route

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.softtanck.navigationcompose.route.navigation.CustomNavigationItem
import com.softtanck.navigationcompose.ui.content.HomeScreen
import com.softtanck.navigationcompose.ui.content.MyScreen

/**
 *
 *  Current name : CustomNavigationRoute in `NavigationCompose`
 *
 *  Created by Tanck on 2021/7/15 3:11 PM.
 *
 *  Note : N/A
 *
 */
class CustomNavigationRoute {


    companion object {
        @JvmStatic
        val instance by lazy(LazyThreadSafetyMode.SYNCHRONIZED) {
            CustomNavigationRoute()
        }
    }

    @Composable
    fun InstallNavigationCore(navigationController: NavHostController) {
        NavHost(navigationController, CustomNavigationItem.HomeScreen.route) {
            composable(CustomNavigationItem.HomeScreen.route) {
                HomeScreen()
            }
            composable(CustomNavigationItem.MyScreen.route) {
                MyScreen()
            }
        }
    }
}