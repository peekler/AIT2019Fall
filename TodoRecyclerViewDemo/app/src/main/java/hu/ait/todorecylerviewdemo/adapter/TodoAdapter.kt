package hu.ait.todorecylerviewdemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.ait.todorecylerviewdemo.R
import hu.ait.todorecylerviewdemo.data.Todo
import kotlinx.android.synthetic.main.todo_row.view.*

class TodoAdapter : RecyclerView.Adapter<TodoAdapter.ViewHolder> {


    var todoList = mutableListOf<Todo>()

    val context: Context
    constructor(context: Context){
        this.context = context

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

        holder.btnDelete.setOnClickListener {
            deleteTodo(holder.adapterPosition)
        }
    }

    fun deleteTodo(index: Int){
        todoList.removeAt(index)
        notifyItemRemoved(index)
    }

    fun addTodo(todo: Todo) {
        todoList.add(todo)
        notifyItemInserted(todoList.lastIndex)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cbTodo = itemView.cbTodo
        val tvDate = itemView.tvDate
        val btnDelete = itemView.btnDelete
    }

}