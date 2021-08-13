package com.dariusz.shoppinglist.ui

import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dariusz.shoppinglist.presentation.components.detail.ShoppingListItemDetail
import com.dariusz.shoppinglist.ui.TestDataSource.getItemsForList
import com.dariusz.shoppinglist.ui.TestUtils.LaunchShoppingListAppElement
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingListDetailTests {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun test_ShowFirstShoppingListContent() {
        initTasks(1)
        composeTestRule.onNodeWithTag("content_element_1").assertIsDisplayed()
    }

    @Test
    fun test_ShowSecondShoppingListContent() {
        initTasks(2)
        composeTestRule.onNodeWithTag("content_element_2").assertIsDisplayed()
    }

    @Test
    fun test_ShowThirdShoppingListContent() {
        initTasks(3)
        composeTestRule.onNodeWithTag("content_element_3").assertIsDisplayed()
    }

    @Test
    fun test_IsFourthListActive() {
        initTasks(4)
        composeTestRule.onNodeWithTag("status_false").assertIsDisplayed()
    }

    @Test
    fun test_IsFifthListActive() {
        initTasks(5)
        composeTestRule.onNodeWithTag("status_false").assertIsDisplayed()
    }

    @Test
    fun test_ShowSixthShoppingList() {
        initTasks(6)
        composeTestRule.onNodeWithTag("status_true").assertIsDisplayed()
    }

    private fun initTasks(listId: Int) {
        composeTestRule.LaunchShoppingListAppElement {
            getItemsForList(listId).forEach {
                ShoppingListItemDetail(it) {}
                Text(
                    it.isDone.toString(),
                    modifier = Modifier.testTag("status_${it.isDone}")
                )
            }
        }
    }

}