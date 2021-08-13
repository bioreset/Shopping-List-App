package com.dariusz.shoppinglist.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dariusz.shoppinglist.utils.DateTimeUtils.determineTodaysDate

@Entity(tableName = "shopping_list_item")
data class ShoppingListItem(
    @ColumnInfo(name = "shopping_list_id")
    val shoppingListId: Int = 0,
    @ColumnInfo(name = "content")
    val content: String = "",
    @ColumnInfo(name = "is_done")
    val isDone: Boolean = false,
    @ColumnInfo(name = "date_of_creation")
    val dateOfCreation: String = "",
    @ColumnInfo(name = "date_of_completion")
    val dateOfCompletion: String = ""
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    companion object {
        fun newShoppingListItem(shoppingListId: Int, content: String) = ShoppingListItem(
            shoppingListId = shoppingListId,
            content = content,
            dateOfCreation = determineTodaysDate()
        )
    }
}

