package com.dariusz.shoppinglist.presentation.components.detail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBasket
import androidx.compose.material.icons.outlined.TaskAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.dariusz.shoppinglist.domain.model.ShoppingListItem
import com.dariusz.shoppinglist.presentation.components.theme.GreenMain
import com.dariusz.shoppinglist.presentation.components.theme.RedMain

@Composable
fun ShoppingListItemDetail(
    item: ShoppingListItem,
    actionOnClick: (Int) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable(true, onClick = {
                actionOnClick.invoke(item.id)
            }),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if (item.isDone) {
            Icon(
                imageVector = Icons.Filled.ShoppingBasket,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp),
                tint = RedMain
            )
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    item.content,
                    style = MaterialTheme.typography.body1,
                    textDecoration = TextDecoration.LineThrough,
                    modifier = Modifier.testTag("content_element_${item.id}")
                )
                Text(
                    "Added at ${item.dateOfCreation}",
                    style = MaterialTheme.typography.caption,
                    color = RedMain
                )
                Text(
                    "Groceries done at ${item.dateOfCompletion}",
                    style = MaterialTheme.typography.caption,
                    color = RedMain
                )
            }
        } else {
            Icon(
                imageVector = Icons.Filled.ShoppingBasket,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp),
                tint = GreenMain
            )
            Column(modifier = Modifier.padding(5.dp)) {
                Text(item.content, style = MaterialTheme.typography.body1)
                Row {
                    Text(
                        "Added at ${item.dateOfCreation}",
                        style = MaterialTheme.typography.caption,
                        color = GreenMain
                    )
                }

            }
            Icon(
                imageVector = Icons.Outlined.TaskAlt,
                contentDescription = "",
                modifier = Modifier
                    .size(30.dp)
                    .padding(end = 10.dp),
                tint = GreenMain
            )
        }
    }
}
