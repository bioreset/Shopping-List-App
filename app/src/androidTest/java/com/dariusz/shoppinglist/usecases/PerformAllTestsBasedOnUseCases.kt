package com.dariusz.shoppinglist.usecases

import androidx.test.core.app.ApplicationProvider
import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListDao
import com.dariusz.shoppinglist.data.local.db.dao.ShoppingListItemDao
import com.dariusz.shoppinglist.database.ProvideDAOs
import com.dariusz.shoppinglist.domain.usecase.*
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PerformAllTestsBasedOnUseCases {

    private lateinit var provideDAOs: ProvideDAOs

    private lateinit var shoppingListDao: ShoppingListDao

    private lateinit var shoppingListItemDao: ShoppingListItemDao

    private lateinit var addNewShoppingItem: AddNewShoppingItem

    private lateinit var addNewShoppingList: AddNewShoppingList

    private lateinit var checkShoppingListItemStatus: CheckShoppingListItemStatus

    private lateinit var checkShoppingListStatus: CheckShoppingListStatus

    private lateinit var markShoppingListItemAsDone: MarkShoppingListItemAsDone

    private lateinit var archiveShoppingList: ArchiveShoppingList

    private lateinit var getLastShoppingListId: GetLastShoppingListId

    private lateinit var countItemsInShoppingList: CountItemsInShoppingList

    private lateinit var countItemsDoneInShoppingList: CountItemsDoneInShoppingList

    @Before
    fun setupTestsAndDAOsAndItems() {
        provideDAOs = ProvideDAOs(ApplicationProvider.getApplicationContext())
        shoppingListDao = provideDAOs.provideShoppingListDao()
        shoppingListItemDao = provideDAOs.provideShoppingListItemDao()
        addNewShoppingList()
        addNewItemToNewShoppingList()
    }

    private fun addNewShoppingList() = runBlocking {
        addNewShoppingList = AddNewShoppingListImpl(shoppingListDao)
        val newlyCreatedShoppingListTitle = "Test shopping list"
        addNewShoppingList.execute(newlyCreatedShoppingListTitle)

    }

    private fun addNewItemToNewShoppingList() = runBlocking {
        addNewShoppingList = AddNewShoppingListImpl(shoppingListDao)
        addNewShoppingItem = AddNewShoppingItemImpl(shoppingListItemDao)
        val newlyCreatedShoppingListTitle = "Test shopping list two"
        addNewShoppingList.execute(newlyCreatedShoppingListTitle)
        val secondShoppingListInDb = shoppingListDao.getAllShoppingLists()[1]
        val newlyCreatedTaskName = "Buy three oranges"
        addNewShoppingItem.execute(secondShoppingListInDb.id, newlyCreatedTaskName)

    }

    @Test
    fun addChecks() = runBlocking {
        val firstShoppingListInDb = shoppingListDao.getAllShoppingLists()[0]
        assert(firstShoppingListInDb.title == "Test shopping list")
        assert(!firstShoppingListInDb.archived)
        assert(firstShoppingListInDb.id == 1)

        val secondShoppingListInDb = shoppingListDao.getAllShoppingLists()[1]
        val firstTaskInSecondList =
            shoppingListItemDao.getAllItemsForShoppingList(secondShoppingListInDb.id)[0]
        assert(secondShoppingListInDb.title == "Test shopping list two")
        assert(!secondShoppingListInDb.archived)
        assert(secondShoppingListInDb.id == 2)
        assert(firstTaskInSecondList.content == "Buy three oranges")
        assert(firstTaskInSecondList.id == 1)
        assert(firstTaskInSecondList.dateOfCompletion == "")
        assert(!firstTaskInSecondList.isDone)
    }

    @Test
    fun checkNewlyAddedShoppingListStatus() = runBlocking {
        checkShoppingListStatus = CheckShoppingListStatusImpl(shoppingListDao)
        val firstShoppingListInDb = shoppingListDao.getAllShoppingLists()[0]
        val status = checkShoppingListStatus.execute(firstShoppingListInDb.id)
        assert(!status) //if archived
    }

    @Test
    fun checkStatusOfNewTaskInShoppingList() = runBlocking {
        checkShoppingListItemStatus = CheckShoppingListItemStatusImpl(shoppingListItemDao)
        val secondShoppingListInDb = shoppingListDao.getAllShoppingLists()[1]
        val firstTaskInSecondList =
            shoppingListItemDao.getAllItemsForShoppingList(secondShoppingListInDb.id)[0]
        val status =
            checkShoppingListItemStatus.execute(secondShoppingListInDb.id, firstTaskInSecondList.id)
        assert(!status) //if done
    }

    @Test
    fun markListAsArchivedAndCheck() = runBlocking {
        archiveShoppingList = ArchiveShoppingListImpl(shoppingListDao)
        archiveShoppingList.execute(2)
        val itemToArchive = shoppingListDao.getAllShoppingLists()[1]
        val archivedItem = shoppingListDao.getArchivedShoppingListsWithItems()[0]
        assert(itemToArchive.archived)
        assert(archivedItem.archived)
        assert(itemToArchive == archivedItem)
    }

    @Test
    fun markTaskAsDoneAndCheck() = runBlocking {
        markShoppingListItemAsDone = MarkShoppingListItemAsDoneImpl(shoppingListItemDao)
        markShoppingListItemAsDone.execute(2, 1)
        val itemToBeDone = shoppingListItemDao.getAllItemsForShoppingList(2)[0]
        assert(itemToBeDone.isDone)
    }

    @Test
    fun getLastShoppingListId() = runBlocking {
        getLastShoppingListId = GetLastShoppingListIdImpl(shoppingListDao)
        val id = getLastShoppingListId.execute()
        assert(id == 2)
    }

    @Test
    fun countItemsInShoppingList() = runBlocking {
        countItemsDoneInShoppingList = CountItemsDoneInShoppingListImpl(shoppingListItemDao)
        countItemsInShoppingList = CountItemsInShoppingListImpl(shoppingListItemDao)
        val doneCount = countItemsDoneInShoppingList.execute(2)
        val allItems = countItemsInShoppingList.execute(2)
        assert(doneCount == 0)
        assert(allItems == 1)
    }

}