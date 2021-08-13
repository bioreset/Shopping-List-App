package com.dariusz.shoppinglist.presentation.components.navigation

sealed class Screens(val route: String, val title: String) {

    sealed class AppScreens(
        route: String,
        title: String
    ) : Screens(
        route,
        title
    ) {

        object ShoppingLists : AppScreens("shoppinglists", "List")
        object ShoppingListItemDetail : AppScreens("shoppinglistitemdetail", "Detail")

    }

}