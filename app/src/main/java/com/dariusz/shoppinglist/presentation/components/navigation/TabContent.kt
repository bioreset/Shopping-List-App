package com.dariusz.shoppinglist.presentation.components.navigation

import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun TabContent(
    pagerState: PagerState,
    actionShowActiveLists: @Composable () -> Unit,
    actionShowArchivedLists: @Composable () -> Unit
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> {
                actionShowActiveLists.invoke()
            }
            1 -> {
                actionShowArchivedLists.invoke()
            }
        }
    }
}