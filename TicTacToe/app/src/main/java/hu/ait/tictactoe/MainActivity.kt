package hu.ait.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import hu.ait.tictactoe.model.TicTacToeModel
import hu.ait.tictactoe.view.TicTacToeView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

   
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btnClear.setOnClickListener {
            ticView.clearGame()
        }


        btnClear.myDemo()
    }

    fun Button.myDemo() {

    }


    fun TicTacToeView.clearGame() {
        TicTacToeModel.resetModel()
        invalidate()
    }


    fun showText(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    fun isFlagOn(): Boolean {
        return btnFlag.isChecked
    }
}
