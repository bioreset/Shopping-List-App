package com.dariusz.shoppinglist.presentation.components.shoppinglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dariusz.shoppinglist.domain.model.ShoppingList
import com.dariusz.shoppinglist.presentation.components.navigation.Screens
import com.dariusz.shoppinglist.utils.NavigationUtils.navigateToWithArgument

@Composable
fun ShoppingListItem(
    navController: NavController,
    item: ShoppingList
) {

    val type = if (item.archived) "archived" else "active"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(true, onClick = {
                navController.navigateToWithArgument(
                    Screens.AppScreens.ShoppingListItemDetail.route,
                    type,
                    item.id
                )
            }),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = Icons.Filled.ShoppingCart,
            contentDescription = "",
            modifier = Modifier.size(25.dp)
        )

        Column(modifier = Modifier.padding(5.dp)) {
            Text("Shopping list #${item.id}: ${item.title}", style = MaterialTheme.typography.body1)
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