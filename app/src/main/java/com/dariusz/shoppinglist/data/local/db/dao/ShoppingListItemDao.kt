package com.dariusz.shoppinglist.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.shoppinglist.domain.model.ShoppingListItem

@Dao
interface ShoppingListItemDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewItem(shoppingListItem: ShoppingListItem)

    @Query("SELECT * FROM shopping_list_item WHERE shopping_list_id=:shoppingListId;")
    suspend fun getAllItemsForShoppingList(shoppingListId: Int): List<ShoppingListItem>

    @Query("UPDATE shopping_list_item SET is_done=1, date_of_completion=:date WHERE shopping_list_id = :shoppingListID AND id = :shoppingListItemId;")
    suspend fun markItemAsDone(shoppingListID: Int, shoppingListItemId: Int, date: String)

    @Query("UPDATE shopping_list_item SET is_done=0, date_of_completion='', date_of_creation=:date WHERE shopping_list_id = :shoppingListID AND id = :shoppingListItemId;")
    suspend fun undoMarkItemAsDone(shoppingListID: Int, shoppingListItemId: Int, date: String)

    @Query("SELECT is_done FROM shopping_list_item WHERE shopping_list_id = :shoppingListID AND id = :shoppingListItemId;")
    suspend fun checkItemStatus(shoppingListID: Int, shoppingListItemId: Int): Boolean

    @Query("SELECT COUNT(*) FROM shopping_list_item WHERE shopping_list_id = :shoppingListId;")
    suspend fun countItemsInShoppingList(shoppingListId: Int): Int

    @Query("SELECT COUNT(*) FROM shopping_list_item WHERE shopping_list_id = :shoppingListId AND is_done=1;")
    suspend fun countItemsDoneInShoppingList(shoppingListId: Int): Int

}