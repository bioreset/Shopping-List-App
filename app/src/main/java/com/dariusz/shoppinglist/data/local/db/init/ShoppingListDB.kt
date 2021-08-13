package com.dariusz.shoppinglist.data.local.db.init

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import com.dariusz.shoppinglist.domain.model.ShoppingList
import com.dariusz.shoppinglist.domain.model.ShoppingListItem
import com.dariusz.shoppinglist.utils.Constants.DB_VERSION

@Database(
    entities = [ShoppingList::class, ShoppingListItem::class],
    version = DB_VERSION,
    exportSchema = false
)
abstract class ShoppingListDB : RoomDatabase() {

    abstract fun shoppingListDao(): ShoppingListDao
    abstract fun shoppingListItemDao(): ShoppingListItemDao

}