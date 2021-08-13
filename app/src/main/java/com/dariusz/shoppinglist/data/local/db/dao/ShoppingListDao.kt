package com.dariusz.shoppinglist.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dariusz.shoppinglist.domain.model.ShoppingList

@Dao
interface ShoppingListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertShoppingList(shoppingList: ShoppingList)

    @Query("UPDATE shopping_lists SET is_archived=1 WHERE id = :shoppingListId;")
    suspend fun archiveShoppingList(shoppingListId: Int)

    @Query("SELECT is_archived FROM shopping_lists WHERE id = :shoppingListId;")
    suspend fun getShoppingListStatus(shoppingListId: Int): Boolean

    @Query("SELECT * FROM shopping_lists WHERE is_archived=0 ORDER BY id ASC;")
    suspend fun getActiveShoppingListsWithItems(): List<ShoppingList>

    @Query("SELECT * FROM shopping_lists WHERE is_archived=1 ORDER BY id ASC;")
    suspend fun getArchivedShoppingListsWithItems(): List<ShoppingList>

    @Query("SELECT * FROM shopping_lists;")
    suspend fun getAllShoppingLists(): List<ShoppingList>  //for testing purposes only; use-case skipped

    @Query("SELECT COUNT(*) FROM shopping_lists;")
    suspend fun getLastShoppingListId(): Int
}