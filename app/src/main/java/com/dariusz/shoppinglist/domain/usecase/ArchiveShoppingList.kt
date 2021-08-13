package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import javax.inject.Inject

interface ArchiveShoppingList {

    suspend fun execute(shoppingListId: Int)

}

class ArchiveShoppingListImpl
@Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : ArchiveShoppingList {

    override suspend fun execute(shoppingListId: Int) =
        shoppingListDao.archiveShoppingList(shoppingListId)


}