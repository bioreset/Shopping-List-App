package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import com.dariusz.shoppinglist.domain.model.ShoppingListItem
import javax.inject.Inject

interface ShowShoppingListDetail {

    suspend fun execute(shoppingListId: Int): List<ShoppingListItem>

}

class ShowShoppingListDetailImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : ShowShoppingListDetail {

    override suspend fun execute(shoppingListId: Int) =
        shoppingListItemDao.getAllItemsForShoppingList(shoppingListId)

}