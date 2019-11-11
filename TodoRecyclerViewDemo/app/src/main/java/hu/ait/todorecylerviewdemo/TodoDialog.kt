package hu.ait.todorecylerviewdemo



import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import hu.ait.todorecylerviewdemo.data.Todo
import kotlinx.android.synthetic.main.new_todo_dialog.view.*
import java.util.*

class TodoDialog : DialogFragment() {

    interface TodoHandler {
        fun todoCreated(item: Todo)
        fun todoUpdated(item: Todo)
    }

    private lateinit var todoHandler: TodoHandler

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is TodoHandler) {
            todoHandler = context
        } else {
            throw RuntimeException(
                "The activity does not implement the TodoHandlerInterface")
        }
    }

    private lateinit var etTodoDate: EditText
    private lateinit var etTodoText: EditText

    var isEditMode = false

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(requireContext())

        builder.setTitle("New todo")

        val rootView = requireActivity().layoutInflater.inflate(
            R.layout.new_todo_dialog, null
        )
        //etTodoDate = rootView.findViewById(R.id.etTodoText)
        etTodoDate = rootView.etDate
        etTodoText = rootView.etTodo
        builder.setView(rootView)

        isEditMode = ((arguments != null) && arguments!!.containsKey(
            ScrollingActivity.KEY_TODO
        ))

        if (isEditMode) {
            builder.setTitle("Edit todo")

            var todo: Todo = (arguments?.getSerializable(ScrollingActivity.KEY_TODO) as Todo)

            etTodoDate.setText(todo.createDate)
            etTodoText.setText(todo.todoText)
        }


        builder.setPositiveButton("OK") {
                dialog, witch -> // empty
        }

        return builder.create()
    }

    override fun onResume() {
        super.onResume()

        val positiveButton = (dialog as AlertDialog).getButton(Dialog.BUTTON_POSITIVE)
        positiveButton.setOnClickListener {
            if (etTodoText.text.isNotEmpty()) {
                if (isEditMode) {
                    handleTodoEdit()
                } else {
                    handleTodoCreate()
                }
                dialog.dismiss()
            } else {
                etTodoText.error = "This field can not be empty"
            }
        }
    }

    private fun handleTodoCreate() {
        todoHandler.todoCreated(
            Todo(
                null,
                Date(System.currentTimeMillis()).toString(),
                etTodoText.text.toString(),
                false
            )
        )
    }

    private fun handleTodoEdit() {
        val todoToEdit = arguments?.getSerializable(
            ScrollingActivity.KEY_TODO
        ) as Todo
        todoToEdit.createDate = etTodoDate.text.toString()
        todoToEdit.todoText = etTodoText.text.toString()

        todoHandler.todoUpdated(todoToEdit)
    }
}