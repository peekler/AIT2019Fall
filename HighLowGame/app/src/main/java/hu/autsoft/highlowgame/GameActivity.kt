package hu.autsoft.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*

class GameActivity : AppCompatActivity() {

    var rand = Random(System.currentTimeMillis())
    var generatedNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        generatedNumber =
            savedInstanceState?.getInt("KEY_GEN") ?: generateNumber()


        btnGuess.setOnClickListener {
            try {

                if (etGuess.text.toString().isNullOrEmpty()) {
                    etGuess.error = "This field can not be empty"
                } else {
                    var guessedNumber = etGuess.text.toString().toInt()
                    if (guessedNumber == generatedNumber) {
                        tvStatus.text = "YOU HAVE WON!"

                        startActivity(Intent(this@GameActivity, ResultActivity::class.java))

                    } else if (guessedNumber < generatedNumber) {
                        tvStatus.text = "Your generatedNumber is smaller"
                    } else if (guessedNumber > generatedNumber) {

                        tvStatus.text = "Your generatedNumber is larger"
                    }
                }

            } catch (e: Exception) {
                tvStatus.text = e.message
            }
        }
    }


    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("KEY_GEN", generatedNumber)

        super.onSaveInstanceState(outState)
    }


    fun generateNumber() = rand.nextInt(5)

}
