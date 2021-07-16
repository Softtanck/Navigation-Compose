package com.softtanck.navigationcompose.route.navigation

import com.softtanck.navigationcompose.R

/**
 *
 *  Current name : CoustomNavigationItem in `NavigationCompose`
 *
 *  Created by Tanck on 2021/7/15 3:08 PM.
 *
 *  Note : N/A
 *
 */
sealed class CustomNavigationItem(val route: String, val icon: Int, val title: String) {
    object HomeScreen : CustomNavigationItem("home", R.drawable.home, "Home")
    object MyScreen: CustomNavigationItem("my", R.drawable.my, "My")
}
