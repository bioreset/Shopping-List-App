package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import javax.inject.Inject

interface CountItemsInShoppingList {

    suspend fun execute(shoppingListId: Int): Int

}

class CountItemsInShoppingListImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : CountItemsInShoppingList {

    override suspend fun execute(shoppingListId: Int) =
        shoppingListItemDao.countItemsInShoppingList(shoppingListId)

}