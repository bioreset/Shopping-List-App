package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import javax.inject.Inject

interface CountItemsDoneInShoppingList {

    suspend fun execute(shoppingListId: Int): Int

}

class CountItemsDoneInShoppingListImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : CountItemsDoneInShoppingList {

    override suspend fun execute(shoppingListId: Int) =
        shoppingListItemDao.countItemsDoneInShoppingList(shoppingListId)

}