package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import com.dariusz.shoppinglist.utils.DateTimeUtils.determineTodaysDate
import javax.inject.Inject

interface MarkShoppingListItemAsDone {

    suspend fun execute(shoppingListId: Int, shoppingListItemId: Int)

}

class MarkShoppingListItemAsDoneImpl
@Inject constructor(
    private val shoppingListItemDao: ShoppingListItemDao
) : MarkShoppingListItemAsDone {

    override suspend fun execute(shoppingListId: Int, shoppingListItemId: Int) =
        shoppingListItemDao.markItemAsDone(
            shoppingListId, shoppingListItemId,
            determineTodaysDate()
        )

}