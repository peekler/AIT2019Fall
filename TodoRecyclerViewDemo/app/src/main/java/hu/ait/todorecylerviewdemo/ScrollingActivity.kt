package hu.ait.todorecylerviewdemo

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import hu.ait.todorecylerviewdemo.adapter.TodoAdapter
import hu.ait.todorecylerviewdemo.data.Todo
import kotlinx.android.synthetic.main.activity_scrolling.*
import java.util.*

class ScrollingActivity : AppCompatActivity() {

    lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)

        setSupportActionBar(toolbar)

        todoAdapter = TodoAdapter(this)
        recyclerTodo.adapter = todoAdapter

        fab.setOnClickListener {
            showAddTodoDialog()
        }
    }

    fun showAddTodoDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enter Todo")
        val input = EditText(this)
        builder.setView(input)

        builder.setPositiveButton("OK", { dialog, which ->
            todoAdapter.addTodo(
                Todo(
                    Date(System.currentTimeMillis()).toString(),
                    input.text.toString(),
                    false)
            )
        })
        builder.setNegativeButton("Cancel") {
                dialog, which -> dialog.cancel()
        }

        builder.show()
    }

}
