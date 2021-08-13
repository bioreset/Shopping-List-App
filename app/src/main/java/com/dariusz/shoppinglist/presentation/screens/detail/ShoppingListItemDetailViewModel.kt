package com.dariusz.shoppinglist.presentation.screens.detail

import androidx.lifecycle.ViewModel
import com.dariusz.shoppinglist.domain.model.ShoppingListItem
import com.dariusz.shoppinglist.domain.usecase.*
import com.dariusz.shoppinglist.utils.ViewModelUtils.launchVMTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ShoppingListItemDetailViewModel
@Inject constructor(
    private val addNewShoppingItem: AddNewShoppingItem,
    private val checkShoppingListItemStatus: CheckShoppingListItemStatus,
    private val markShoppingListItemAsDone: MarkShoppingListItemAsDone,
    private val undoMarkShoppingListItemAsDone: UndoMarkShoppingListItemAsDone,
    private val showShoppingListDetail: ShowShoppingListDetail
) : ViewModel() {

    private var _shoppingItems =
        MutableStateFlow<List<ShoppingListItem>>(
            listOf()
        )
    val shoppingItems: StateFlow<List<ShoppingListItem>> =
        _shoppingItems

    fun getAllShoppingItems(shoppingListId: Int) = launchVMTask {
        _shoppingItems.value = showShoppingListDetail.execute(shoppingListId)

    }

    fun markorUndoMarkShoppingListItemAsDone(shoppingListId: Int, shoppingListItemId: Int) =
        launchVMTask {
            val checkShoppingItemStatus = checkShoppingListItemStatus.execute(
                shoppingListId,
                shoppingListItemId,
            )
            if (checkShoppingItemStatus)
                undoMarkShoppingListItemAsDone.execute(
                    shoppingListId,
                    shoppingListItemId
                )
            else
                markShoppingListItemAsDone.execute(
                    shoppingListId,
                    shoppingListItemId
                )
        }

    fun addNewShoppingListItem(shoppingListId: Int, shoppingItemContent: String) = launchVMTask {
        addNewShoppingItem.execute(
            shoppingListId,
            shoppingItemContent
        )
    }

}