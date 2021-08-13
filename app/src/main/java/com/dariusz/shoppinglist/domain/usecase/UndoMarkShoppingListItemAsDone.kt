package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import com.dariusz.shoppinglist.utils.DateTimeUtils.determineTodaysDate
import javax.inject.Inject

interface UndoMarkShoppingListItemAsDone {

    suspend fun execute(shoppingListId: Int, shoppingListItemId: Int)

}

class UndoMarkShoppingListItemAsDoneImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : UndoMarkShoppingListItemAsDone {

    override suspend fun execute(shoppingListId: Int, shoppingListItemId: Int) =
        shoppingListItemDao.undoMarkItemAsDone(
            shoppingListId,
            shoppingListItemId,
            determineTodaysDate()
        )

}