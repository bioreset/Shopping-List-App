package com.dariusz.shoppinglist.presentation.components.shoppinglist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.dariusz.shoppinglist.domain.model.ShoppingList

@Composable
fun ListOfArchivedShoppingLists(
    input: List<ShoppingList>,
    navController: NavController
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(input) { itemList ->
            ShoppingListItem(navController, itemList)
        }
    }
}