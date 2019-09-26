package hu.ait.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClear.setOnClickListener {
            ticView.clearGame()
        }
    }

    fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun isFlagOn(): Boolean {
        return btnFlag.isChecked
    }
}
