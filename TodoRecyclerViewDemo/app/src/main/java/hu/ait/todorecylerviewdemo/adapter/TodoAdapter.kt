package hu.ait.todorecylerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.todorecylerviewdemo.R
import hu.ait.todorecylerviewdemo.ScrollingActivity
import hu.ait.todorecylerviewdemo.data.AppDatabase
import hu.ait.todorecylerviewdemo.data.Todo
import hu.ait.todorecylerviewdemo.touch.TodoTouchHelperCallback
import kotlinx.android.synthetic.main.todo_row.view.*
import java.util.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder>, TodoTouchHelperCallback {

    var todoList = mutableListOf<Todo>()

    val context: Context
    constructor(context: Context, todos: List<Todo>){
        this.context = context

        todoList.addAll(todos)

        //for (i in 0..20){
        //    todoList.add(Todo("2019", "Todo $i", false))
        //}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val todoRow = LayoutInflater.from(context).inflate(
            R.layout.todo_row, parent, false
        )
        return ViewHolder(todoRow)
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var todo = todoList.get(holder.adapterPosition)

        holder.cbTodo.text = todo.todoText
        holder.cbTodo.isChecked = todo.done
        holder.tvDate.text = todo.createDate

        if (todo.category == 1) {
            holder.ivIcon.setImageResource(R.drawable.todohead)
        } else if (todo.category == 2) {
            holder.ivIcon.setImageResource(R.mipmap.ic_launcher)
        }

        holder.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }

        holder.cbTodo.setOnClickListener {
            todo.done = holder.cbTodo.isChecked
            updateTodo(todo)
        }

        holder.btnEdit.setOnClickListener {
            (context as ScrollingActivity).showEditTodoDialog(
                todo, holder.adapterPosition
            )
        }
    }

    fun updateTodo(todo: Todo) {
        Thread {
            AppDatabase.getInstance(context).todoDao().updateTodo(todo)
        }.start()
    }

    fun updateTodoOnPosition(todo: Todo, index: Int) {
        todoList.set(index, todo)
        notifyItemChanged(index)
    }


    fun deleteTodo(index: Int){
        Thread{
            AppDatabase.getInstance(context).todoDao().deleteTodo(todoList[index])

            (context as ScrollingActivity).runOnUiThread {
                todoList.removeAt(index)
                notifyItemRemoved(index)
            }
        }.start()
    }

    fun deleteAllTodos() {
        Thread {
            AppDatabase.getInstance(context).todoDao().deleteAllTodo()

            (context as ScrollingActivity).runOnUiThread {
                todoList.clear()
                notifyDataSetChanged()
            }
        }.start()
    }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
        notifyItemInserted(todoList.lastIndex)
    }


    override fun onDismissed(position: Int) {
        deleteTodo(position)
    }

    override fun onItemMoved(fromPosition: Int, toPosition: Int) {
        Collections.swap(todoList, fromPosition, toPosition)
        notifyItemMoved(fromPosition, toPosition)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbTodo = itemView.cbTodo
        val tvDate = itemView.tvDate
        val btnDelete = itemView.btnDelete
        val btnEdit = itemView.btnEdit
        val ivIcon = itemView.ivIcon
    }

}