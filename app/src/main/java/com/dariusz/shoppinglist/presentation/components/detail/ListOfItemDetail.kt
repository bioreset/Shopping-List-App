package com.dariusz.shoppinglist.presentation.components.detail

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dariusz.shoppinglist.domain.model.ShoppingListItem

@Composable
fun ListOfItemDetails(
    input: List<ShoppingListItem>,
    actionMarkOrUnmarkDone: (Int) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(input) { itemsList ->
            ShoppingListItemDetail(itemsList) {
                actionMarkOrUnmarkDone.invoke(it)
            }
        }
    }
}

@Composable
fun ListOfItemDetailsArchived(
    input: List<ShoppingListItem>
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(input) { itemsList ->
            ShoppingListItemDetail(itemsList) {}
        }
    }
}