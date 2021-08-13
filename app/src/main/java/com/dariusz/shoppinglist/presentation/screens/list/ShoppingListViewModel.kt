package com.dariusz.shoppinglist.presentation.screens.list

import androidx.lifecycle.ViewModel
import com.dariusz.shoppinglist.domain.model.ShoppingList
import com.dariusz.shoppinglist.domain.usecase.*
import com.dariusz.shoppinglist.utils.ViewModelUtils.launchVMTask
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel
@Inject constructor(
    private val addNewShoppingList: AddNewShoppingList,
    private val archiveShoppingList: ArchiveShoppingList,
    private val checkShoppingListStatus: CheckShoppingListStatus,
    private val showActiveShoppingLists: ShowActiveShoppingLists,
    private val showArchivedShoppingLists: ShowArchivedShoppingLists,
    private val countItemsDoneInShoppingList: CountItemsDoneInShoppingList,
    private val countItemsInShoppingList: CountItemsInShoppingList,
    private val getLastShoppingListId: GetLastShoppingListId
) : ViewModel() {

    private var _activeShoppingLists =
        MutableStateFlow<List<ShoppingList>>(
            listOf()
        )
    val activeShoppingLists: StateFlow<List<ShoppingList>> =
        _activeShoppingLists

    private var _archivedShoppingLists =
        MutableStateFlow<List<ShoppingList>>(
            listOf()
        )
    val archivedShoppingLists: StateFlow<List<ShoppingList>> =
        _archivedShoppingLists

    private var _lastShoppingListId =
        MutableStateFlow<Int>(
            0
        )
    val lastShoppingListId: StateFlow<Int> =
        _lastShoppingListId

    fun getActiveShoppingLists() = launchVMTask {
        _activeShoppingLists.value = showActiveShoppingLists.execute()
        _activeShoppingLists.value.addCounts()
    }

    fun getArchivedShoppingLists() = launchVMTask {
        _archivedShoppingLists.value = showArchivedShoppingLists.execute()
    }

    fun determineShoppingListState() = launchVMTask {
        _activeShoppingLists.value.forEach { item ->
            if (item.itemsDone == item.itemsCount &&
                !checkShoppingListStatus.execute(item.id)
            )
                archiveShoppingList.execute(item.id)
        }
    }

    fun addNewShoppingList(name: String) = launchVMTask {
        addNewShoppingList.execute(
            name
        )
    }

    fun getLastShoppingListId() = launchVMTask {
        _lastShoppingListId.value = getLastShoppingListId.execute()
    }

    private suspend fun List<ShoppingList>.addCounts() = forEach {
        it.itemsDone = countItemsDoneInShoppingList.execute(it.id)
        it.itemsCount = countItemsInShoppingList.execute(it.id)
    }


}
