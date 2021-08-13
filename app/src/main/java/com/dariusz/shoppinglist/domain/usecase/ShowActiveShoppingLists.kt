package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import com.dariusz.shoppinglist.domain.model.ShoppingList
import javax.inject.Inject

interface ShowActiveShoppingLists {

    suspend fun execute(): List<ShoppingList>

}

class ShowActiveShoppingListsImpl
@Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ShowActiveShoppingLists {

    override suspend fun execute() =
        shoppingListDao.getActiveShoppingListsWithItems()


}