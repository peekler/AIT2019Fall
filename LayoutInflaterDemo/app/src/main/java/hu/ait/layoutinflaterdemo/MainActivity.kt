package hu.ait.layoutinflaterdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.todo_layout.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnAdd.setOnClickListener {
            addTodo()
        }
    }

    private fun addTodo() {
        if (etTodo.text.isNotEmpty()) {

            var todoView = layoutInflater.inflate(
                R.layout.todo_layout, null, false
            )

            todoView.tvTodo.text = etTodo.text.toString()

            if (cbHighPrio.isChecked) {
                todoView.ivTodoPrority.setImageResource(
                    android.R.drawable.ic_notification_overlay
                )
            }

            todoView.btnDelete.setOnClickListener {
                layoutContent.removeView(todoView)
            }


            layoutContent.addView(todoView, 0)

            etTodo.setText("")
        } else {
            etTodo.error = "This field can not be empty"
        }
    }

}
