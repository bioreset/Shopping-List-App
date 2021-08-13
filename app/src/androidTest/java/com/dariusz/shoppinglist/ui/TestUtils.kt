package com.dariusz.shoppinglist.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import com.dariusz.shoppinglist.presentation.components.theme.ShoppingListTheme

object TestUtils {

    fun ComposeContentTestRule.LaunchShoppingListAppElement(
        element: @Composable () -> Unit
    ) {
        setContent {
            ShoppingListTheme {
                element.invoke()
            }
        }
    }

}