package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import javax.inject.Inject

interface CheckShoppingListItemStatus {

    suspend fun execute(shoppingListId: Int, shoppingListItemId: Int): Boolean

}

class CheckShoppingListItemStatusImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : CheckShoppingListItemStatus {

    override suspend fun execute(shoppingListId: Int, shoppingListItemId: Int) =
        shoppingListItemDao.checkItemStatus(shoppingListId, shoppingListItemId)

}