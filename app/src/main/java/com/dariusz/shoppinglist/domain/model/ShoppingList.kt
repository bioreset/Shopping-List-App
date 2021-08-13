package com.dariusz.shoppinglist.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.dariusz.shoppinglist.utils.DateTimeUtils.determineTodaysDate

@Entity(tableName = "shopping_lists")
data class ShoppingList(
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "is_archived")
    val archived: Boolean = false,
    @ColumnInfo(name = "date_of_creation")
    val dateOfCreation: String = ""
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0

    @Ignore
    var itemsCount: Int = 0

    @Ignore
    var itemsDone: Int = 0

    companion object {
        fun newShoppingList(title: String) = ShoppingList(
            title = title,
            dateOfCreation = determineTodaysDate()
        )
    }
}
