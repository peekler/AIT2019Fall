package hu.ait.todorecylerviewdemo.data

import androidx.room.*

@Dao
interface TodoDao {

    @Query("SELECT * FROM todo")
    fun getAllTodo() : List<Todo>

    @Insert
    fun addTodo(todo: Todo) : Long

    @Delete
    fun deleteTodo(todo: Todo)

    @Update
    fun updateTodo(todo: Todo)

    @Query("DELETE FROM todo")
    fun deleteAllTodo()
}