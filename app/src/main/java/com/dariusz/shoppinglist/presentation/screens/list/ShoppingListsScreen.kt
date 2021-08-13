package com.dariusz.shoppinglist.presentation.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.dariusz.shoppinglist.di.UseCasesModule.provideAddNewShoppingListAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideArchiveShoppingListAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideCheckShoppingListStatusAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideCountItemsDoneInShoppingListAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideCountItemsInShoppingListAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideGetLastShoppingListIdAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideShowActiveShoppingListAction
import com.dariusz.shoppinglist.di.UseCasesModule.provideShowArchivedShoppingListAction
import com.dariusz.shoppinglist.presentation.components.common.TextInput
import com.dariusz.shoppinglist.presentation.components.common.TopBar
import com.dariusz.shoppinglist.presentation.components.navigation.Screens
import com.dariusz.shoppinglist.presentation.components.navigation.TabContent
import com.dariusz.shoppinglist.presentation.components.navigation.TabLayout
import com.dariusz.shoppinglist.presentation.components.shoppinglist.ListOfActiveShoppingLists
import com.dariusz.shoppinglist.presentation.components.shoppinglist.ListOfArchivedShoppingLists
import com.dariusz.shoppinglist.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.shoppinglist.utils.ViewModelUtils.composeViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState

@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun ShoppingListsScreen(
    pagerState: PagerState,
    navController: NavController
) {
    val viewModel = initShoppingListViewModel()

    val activeLists by remember(viewModel) {
        viewModel.activeShoppingLists
    }.collectAsState()

    val archivedLists by remember(viewModel) {
        viewModel.archivedShoppingLists
    }.collectAsState()

    val lastId by remember(viewModel) {
        viewModel.lastShoppingListId
    }.collectAsState()

    var textInputVisible by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopBar("Shopping Lists")
        },
        content = {
            Column {
                TabLayout(pagerState)
                TabContent(
                    pagerState = pagerState,
                    actionShowActiveLists = {
                        ListOfActiveShoppingLists(
                            input = activeLists,
                            navController = navController
                        )
                    },
                    actionShowArchivedLists = {
                        ListOfArchivedShoppingLists(
                            input = archivedLists,
                            navController = navController
                        )
                    }
                )
            }
        },
        bottomBar = {
            if (textInputVisible)
                TextInput(
                    placeholder = "Add new shopping list",
                    action = { input ->
                        viewModel.apply {
                            addNewShoppingList(input)
                            getActiveShoppingLists()
                            getArchivedShoppingLists()
                            determineShoppingListState()
                            getLastShoppingListId()
                        }
                        textInputVisible = false
                        navController.navigateToWithArgument(
                            Screens.AppScreens.ShoppingListItemDetail.route,
                            "active",
                            lastId + 1
                        )
                    }
                )
        },
        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    textInputVisible = true
                },
                content = {
                    Icon(Icons.Filled.Add, "")
                }
            )
        }
    )
    viewModel.apply {
        getActiveShoppingLists()
        getArchivedShoppingLists()
        determineShoppingListState()
        getLastShoppingListId()
    }
}

@Composable
fun initShoppingListViewModel(): ShoppingListViewModel {
    val currentContext = LocalContext.current
    return composeViewModel {
        ShoppingListViewModel(
            provideAddNewShoppingListAction(currentContext),
            provideArchiveShoppingListAction(currentContext),
            provideCheckShoppingListStatusAction(currentContext),
            provideShowActiveShoppingListAction(currentContext),
            provideShowArchivedShoppingListAction(currentContext),
            provideCountItemsDoneInShoppingListAction(currentContext),
            provideCountItemsInShoppingListAction(currentContext),
            provideGetLastShoppingListIdAction(currentContext)
        )
    }
}
