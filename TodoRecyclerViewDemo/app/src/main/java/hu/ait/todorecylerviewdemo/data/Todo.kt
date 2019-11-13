package hu.ait.todorecylerviewdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey(autoGenerate = true) var todoId: Long?,
    @ColumnInfo(name = "createDate") var createDate: String,
    @ColumnInfo(name = "todoText") var todoText: String,
    @ColumnInfo(name = "done") var done: Boolean,
    @ColumnInfo(name = "category") var category: Int = 1

) : Serializable