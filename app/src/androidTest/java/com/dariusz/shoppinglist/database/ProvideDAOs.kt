package com.dariusz.shoppinglist.database

import android.content.Context
import androidx.room.Room
import com.dariusz.shoppinglist.data.local.db.init.ShoppingListDB

class ProvideDAOs(private val context: Context) {

    private fun provideDatabaseBuilderForTests(): ShoppingListDB {
        return Room.inMemoryDatabaseBuilder(context, ShoppingListDB::class.java).build()
    }

    fun provideShoppingListDao() = provideDatabaseBuilderForTests().shoppingListDao()

    fun provideShoppingListItemDao() = provideDatabaseBuilderForTests().shoppingListItemDao()

}