package hu.autsoft.highlowgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    var rand = Random(System.currentTimeMillis())
    var number = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        generateNumber()

        btnGuess.setOnClickListener {
            var guessedNumber = etGuess.text.toString().toInt()
            if (guessedNumber == number) {
                tvStatus.text = "YOU HAVE WON!"
            } else if (guessedNumber < number) {
                tvStatus.text = "Your number is smaller"
            } else if (guessedNumber > number) {
                tvStatus.text = "Your number is larger"
            }
        }
    }

    fun generateNumber(){
        number = rand.nextInt(100)
    }
}
