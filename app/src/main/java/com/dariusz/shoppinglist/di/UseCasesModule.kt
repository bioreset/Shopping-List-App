package com.dariusz.shoppinglist.di

import android.content.Context
import com.dariusz.shoppinglist.di.DatabaseModule.provideDatabaseBuilder
import com.dariusz.shoppinglist.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    @Singleton
    fun provideAddNewShoppingItemAction(@ApplicationContext context: Context): AddNewShoppingItem =
        AddNewShoppingItemImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideAddNewShoppingListAction(@ApplicationContext context: Context): AddNewShoppingList =
        AddNewShoppingListImpl(provideDatabaseBuilder(context).shoppingListDao())

    @Provides
    @Singleton
    fun provideArchiveShoppingListAction(@ApplicationContext context: Context): ArchiveShoppingList =
        ArchiveShoppingListImpl(provideDatabaseBuilder(context).shoppingListDao())

    @Provides
    @Singleton
    fun provideCheckShoppingListItemStatusAction(@ApplicationContext context: Context): CheckShoppingListItemStatus =
        CheckShoppingListItemStatusImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideCheckShoppingListStatusAction(@ApplicationContext context: Context): CheckShoppingListStatus =
        CheckShoppingListStatusImpl(provideDatabaseBuilder(context).shoppingListDao())

    @Provides
    @Singleton
    fun provideMarkShoppingListItemAsDoneAction(@ApplicationContext context: Context): MarkShoppingListItemAsDone =
        MarkShoppingListItemAsDoneImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideUndoMarkShoppingListItemAsDoneAction(@ApplicationContext context: Context): UndoMarkShoppingListItemAsDone =
        UndoMarkShoppingListItemAsDoneImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideShowArchivedShoppingListAction(@ApplicationContext context: Context): ShowArchivedShoppingLists =
        ShowArchivedShoppingListsImpl(provideDatabaseBuilder(context).shoppingListDao())

    @Provides
    @Singleton
    fun provideShowActiveShoppingListAction(@ApplicationContext context: Context): ShowActiveShoppingLists =
        ShowActiveShoppingListsImpl(provideDatabaseBuilder(context).shoppingListDao())

    @Provides
    @Singleton
    fun provideShowShoppingListDetailAction(@ApplicationContext context: Context): ShowShoppingListDetail =
        ShowShoppingListDetailImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideCountItemsDoneInShoppingListAction(@ApplicationContext context: Context): CountItemsDoneInShoppingList =
        CountItemsDoneInShoppingListImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideCountItemsInShoppingListAction(@ApplicationContext context: Context): CountItemsInShoppingList =
        CountItemsInShoppingListImpl(provideDatabaseBuilder(context).shoppingListItemDao())

    @Provides
    @Singleton
    fun provideGetLastShoppingListIdAction(@ApplicationContext context: Context): GetLastShoppingListId =
        GetLastShoppingListIdImpl(provideDatabaseBuilder(context).shoppingListDao())
}