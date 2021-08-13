package com.dariusz.shoppinglist.utils

import androidx.navigation.NavController

object NavigationUtils {

    fun <R, T> NavController.navigateToWithArgument(
        screenRoute: String,
        type: R?,
        argument: T
    ) = navigate(screenRoute.plus("/$type/$argument")) {
        graph.startDestinationRoute?.let { route ->
            popUpTo(route)
        }
    }

}