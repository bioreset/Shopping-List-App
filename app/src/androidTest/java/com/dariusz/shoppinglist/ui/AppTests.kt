package com.dariusz.shoppinglist.ui

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dariusz.shoppinglist.presentation.components.navigation.CustomNavigator
import com.dariusz.shoppinglist.ui.TestUtils.LaunchShoppingListAppElement
import com.google.accompanist.pager.ExperimentalPagerApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
class AppTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.LaunchShoppingListAppElement {
            CustomNavigator()
        }
    }

    @Test
    fun test_IfFirstScreenIsListScreen() {
        composeTestRule.onNodeWithText("Shopping Lists").assertIsDisplayed()
    }

    @Test
    fun test_AreTabsShown() {
        composeTestRule.onNodeWithText("Shopping Lists").assertIsDisplayed()
        composeTestRule.onNodeWithText("Archived Shopping Lists").assertIsDisplayed()
    }

}