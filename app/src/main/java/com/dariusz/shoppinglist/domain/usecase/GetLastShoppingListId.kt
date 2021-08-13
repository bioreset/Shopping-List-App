package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import javax.inject.Inject

interface GetLastShoppingListId {

    suspend fun execute(): Int

}

class GetLastShoppingListIdImpl
@Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : GetLastShoppingListId {

    override suspend fun execute() =
        shoppingListDao.getLastShoppingListId()

}