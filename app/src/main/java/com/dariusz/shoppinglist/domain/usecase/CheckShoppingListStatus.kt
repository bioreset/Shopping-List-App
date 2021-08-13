package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import javax.inject.Inject

interface CheckShoppingListStatus {

    suspend fun execute(shoppingListId: Int): Boolean

}

class CheckShoppingListStatusImpl
@Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : CheckShoppingListStatus {

    override suspend fun execute(shoppingListId: Int) =
        shoppingListDao.getShoppingListStatus(shoppingListId)


}