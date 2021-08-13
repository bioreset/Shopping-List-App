package com.dariusz.shoppinglist.presentation.components.common

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.dariusz.shoppinglist.presentation.components.theme.GreenVariant

@Composable
fun TopBar(text: String) {
    TopAppBar(
        title = { Text(text) },
        backgroundColor = GreenVariant,
        contentColor = Color.White
    )
}