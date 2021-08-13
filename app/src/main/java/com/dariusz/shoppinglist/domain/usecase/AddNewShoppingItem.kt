package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import com.dariusz.shoppinglist.domain.model.ShoppingListItem.Companion.newShoppingListItem
import javax.inject.Inject

interface AddNewShoppingItem {

    suspend fun execute(shoppingListId: Int, shoppingItemContent: String)

}

class AddNewShoppingItemImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : AddNewShoppingItem {

    override suspend fun execute(shoppingListId: Int, shoppingItemContent: String) {
        shoppingListItemDao.insertNewItem(
            newShoppingListItem(
                shoppingListId = shoppingListId,
                content = shoppingItemContent
            )
        )
    }
}