package hu.autsoft.highlowgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            var intentGame = Intent()
            intentGame.setClass(this@MainActivity,
                GameActivity::class.java)
            startActivity(intentGame)
        }
    }
}
