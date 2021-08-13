package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import com.dariusz.shoppinglist.domain.model.ShoppingList
import javax.inject.Inject

interface ShowArchivedShoppingLists {

    suspend fun execute(): List<ShoppingList>

}

class ShowArchivedShoppingListsImpl
@Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShowArchivedShoppingLists {

    override suspend fun execute() =
        shoppingListDao.getArchivedShoppingListsWithItems()

}