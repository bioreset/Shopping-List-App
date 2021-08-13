package com.dariusz.shoppinglist.presentation.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import com.dariusz.shoppinglist.di.UseCasesModule.provideAddNewShoppingItemAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideCheckShoppingListItemStatusAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideMarkShoppingListItemAsDoneAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideShowShoppingListDetailAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideUndoMarkShoppingListItemAsDoneAction
import com.dariusz.shoppinglist.presentation.components.common.TextInput
import com.dariusz.shoppinglist.presentation.components.common.TopBar
import com.dariusz.shoppinglist.presentation.components.detail.ListOfItemDetails
import com.dariusz.shoppinglist.presentation.components.detail.ListOfItemDetailsArchived
import com.dariusz.shoppinglist.utils.ViewModelUtils.composeViewModel

@ExperimentalComposeUiApi
@Composable
fun ShoppingListItemDetail(
    type: String,
    shoppingListID: Int
) {

    val viewModel = initShoppingListItemDetailViewModel()

    val listItems by remember(viewModel) {
        viewModel.shoppingItems
    }.collectAsState()

    var textInputVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar("Shopping List #$shoppingListID")
        },
        content = {
            Column {
                if (type == "active") {
                    ListOfItemDetails(listItems) {
                        viewModel.markorUndoMarkShoppingListItemAsDone(
                            shoppingListID,
                            it
                        )
                    }
                } else {
                    ListOfItemDetailsArchived(listItems)
                }
            }
        },
        bottomBar = {
            if (textInputVisible && type == "active")
                TextInput(
                    placeholder = "Add new shopping item",
                    action = { input ->
                        viewModel.apply {
                            addNewShoppingListItem(
                                shoppingListID,
                                input
                            )
                            getAllShoppingItems(shoppingListID)
                        }
                        textInputVisible = false
                    }
                )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            if (type == "active")
                FloatingActionButton(
                    onClick = {
                        textInputVisible = true
                    },
                    content = {
                        Icon(Icons.Filled.Add, "")
                    }
                )
        }
    )

    viewModel.getAllShoppingItems(shoppingListID)

}

@Composable
fun initShoppingListItemDetailViewModel(): ShoppingListItemDetailViewModel {
    val currentContext = LocalContext.current
    return composeViewModel {
        ShoppingListItemDetailViewModel(
            provideAddNewShoppingItemAction(currentContext),
            provideCheckShoppingListItemStatusAction(currentContext),
            provideMarkShoppingListItemAsDoneAction(currentContext),
            provideUndoMarkShoppingListItemAsDoneAction(currentContext),
            provideShowShoppingListDetailAction(currentContext)
        )
    }
}