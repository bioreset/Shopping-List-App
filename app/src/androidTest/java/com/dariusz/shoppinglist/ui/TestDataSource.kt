package com.dariusz.shoppinglist.ui

import com.dariusz.shoppinglist.domain.model.ShoppingList
import com.dariusz.shoppinglist.domain.model.ShoppingListItem
import com.dariusz.shoppinglist.utils.DateTimeUtils.determineTodaysDate

object TestDataSource {

    val testShoppingList = listOf(
        ShoppingList("first list", false, determineTodaysDate()),
        ShoppingList("second list", true, determineTodaysDate()),
        ShoppingList("third list", true, determineTodaysDate()),
        ShoppingList("fourth list", false, determineTodaysDate()),
        ShoppingList("fifth list", false, determineTodaysDate()),
        ShoppingList("sixth list", true, determineTodaysDate())
    )

    private val testShoppingListItems = listOf(
        ShoppingListItem(1, "buy milk", true, "", determineTodaysDate()),
        ShoppingListItem(1, "buy orange", false, determineTodaysDate(), ""),
        ShoppingListItem(2, "buy pepsi", true, "", determineTodaysDate()),
        ShoppingListItem(2, "buy water", true, "", determineTodaysDate()),
        ShoppingListItem(3, "buy banana", true, "", determineTodaysDate()),
        ShoppingListItem(4, "buy kfc", false, determineTodaysDate(), ""),
        ShoppingListItem(5, "buy iphone", false, determineTodaysDate(), ""),
        ShoppingListItem(6, "buy pizza", true, "", determineTodaysDate())
    )

    fun getItemsForList(id: Int): List<ShoppingListItem> = testShoppingListItems.filter {
        it.id == id
    }

}