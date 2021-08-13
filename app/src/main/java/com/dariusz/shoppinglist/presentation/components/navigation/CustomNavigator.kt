package com.dariusz.shoppinglist.presentation.components.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dariusz.shoppinglist.presentation.screens.detail.ShoppingListItemDetail
import com.dariusz.shoppinglist.presentation.screens.list.ShoppingListsScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun CustomNavigator() {
    val navController = rememberNavController()
    val pagerState = rememberPagerState(pageCount = 2)
    NavHost(
        navController = navController,
        startDestination = Screens.AppScreens.ShoppingLists.route
    ) {
        composable(route = Screens.AppScreens.ShoppingLists.route) {
            ShoppingListsScreen(
                pagerState,
                navController
            )
        }
        composable(route = Screens.AppScreens.ShoppingListItemDetail.route.plus("/{type}/{shopping_list_id}")) {
            ShoppingListItemDetail(
                type = it.arguments?.getString("type") ?: "",
                shoppingListID = it.arguments?.getString("shopping_list_id")?.toInt() ?: 0
            )
        }
    }

}