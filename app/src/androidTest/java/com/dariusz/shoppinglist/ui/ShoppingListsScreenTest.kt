package com.dariusz.shoppinglist.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.dariusz.shoppinglist.domain.model.ShoppingList
import com.dariusz.shoppinglist.ui.TestDataSource.testShoppingList
import com.dariusz.shoppinglist.ui.TestUtils.LaunchShoppingListAppElement
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShoppingListsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.LaunchShoppingListAppElement {
            testShoppingList.forEach {
                ShowList(item = it)
            }
        }
    }

    @Test
    fun test_FirstElement() {
        composeTestRule.onNodeWithTag("text_of_element_1").assertIsDisplayed()
    }

    @Test
    fun test_SecondToSixthElement() {
        composeTestRule.onNodeWithTag("text_of_element_2").assertIsDisplayed()
        composeTestRule.onNodeWithTag("text_of_element_3").assertIsDisplayed()
        composeTestRule.onNodeWithTag("text_of_element_4").assertIsDisplayed()
        composeTestRule.onNodeWithTag("text_of_element_5").assertIsDisplayed()
        composeTestRule.onNodeWithTag("text_of_element_6").assertIsDisplayed()
    }

    @Composable
    private fun ShowList(item: ShoppingList) {

        val type = if (item.archived) "archived" else "active"

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    "Shopping list #${item.id}: ${item.title}",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.testTag("text_of_element_${item.id}")
                )
                if (type == "active") {
                    Text(
                        "Groceries done ${item.itemsDone} / ${item.itemsCount}",
                        style = MaterialTheme.typography.caption
                    )
                } else {
                    Text(
                        "All groceries done",
                        style = MaterialTheme.typography.caption
                    )
                }

            }

        }
    }

}