package hu.ait.todorecylerviewdemo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.GridLayout
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import hu.ait.todorecylerviewdemo.adapter.TodoAdapter
import hu.ait.todorecylerviewdemo.data.AppDatabase
import hu.ait.todorecylerviewdemo.data.Todo
import hu.ait.todorecylerviewdemo.touch.TodoReyclerTouchCallback
import kotlinx.android.synthetic.main.activity_scrolling.*
import java.util.*

class ScrollingActivity : AppCompatActivity(), TodoDialog.TodoHandler {


    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        initRecyclerView()


        fab.setOnClickListener {
            showAddTodoDialog()
        }
    }

    private fun initRecyclerView() {
        Thread {
            var todoList =
                AppDatabase.getInstance(this@ScrollingActivity).todoDao().getAllTodo()

            runOnUiThread {
                todoAdapter = TodoAdapter(this, todoList)
                recyclerTodo.adapter = todoAdapter

                var itemDecoration = DividerItemDecoration(
                    this,
                    DividerItemDecoration.VERTICAL
                )
                recyclerTodo.addItemDecoration(itemDecoration)

                //recyclerTodo.layoutManager =
                //    GridLayoutManager(this, 2)

                val callback = TodoReyclerTouchCallback(todoAdapter)
                val touchHelper = ItemTouchHelper(callback)
                touchHelper.attachToRecyclerView(recyclerTodo)
            }
        }.start()
    }

    fun showAddTodoDialog() {
        TodoDialog().show(supportFragmentManager, "TAG_TODO_DIALOG")
    }

    fun saveTodo(todo: Todo) {
        Thread {
            AppDatabase.getInstance(this).todoDao().insertTodo(
                todo
            )

            runOnUiThread {
                todoAdapter.addTodo(todo)
            }
        }.start()
    }

    override fun todoCreated(item: Todo) {
        saveTodo(item)
    }
}
