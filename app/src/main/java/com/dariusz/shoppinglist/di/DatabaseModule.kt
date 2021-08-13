package com.dariusz.shoppinglist.di

import android.content.Context
import androidx.room.Room
import com.dariusz.shoppinglist.data.local.db.init.ShoppingListDB
import com.dariusz.shoppinglist.utils.Constants.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseBuilder(@ApplicationContext context: Context): ShoppingListDB {
        return Room.databaseBuilder(context, ShoppingListDB::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


}