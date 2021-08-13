package com.dariusz.shoppinglist.domain.usecase

import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import com.dariusz.shoppinglist.domain.model.ShoppingList.Companion.newShoppingList
import javax.inject.Inject

interface AddNewShoppingList {

    suspend fun execute(shoppingListName: String)

}

class AddNewShoppingListImpl
@Inject constructor(
    private val shoppingListDao: ShoppingListDao
) : AddNewShoppingList {

    override suspend fun execute(shoppingListName: String) =
        shoppingListDao.insertShoppingList(
            newShoppingList(shoppingListName)
        )

}