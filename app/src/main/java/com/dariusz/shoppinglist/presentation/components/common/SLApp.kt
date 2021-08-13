package com.dariusz.shoppinglist.presentation.components.common

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import com.dariusz.shoppinglist.presentation.components.navigation.CustomNavigator
import com.dariusz.shoppinglist.presentation.components.theme.ShoppingListTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun SLApp() {
    ShoppingListTheme {
        CustomNavigator()
    }
}