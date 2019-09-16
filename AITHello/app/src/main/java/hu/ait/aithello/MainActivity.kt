package hu.ait.aithello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "MainActivity has started")

        var myCar = Car("Tesla Model X")

        btnShow.setOnClickListener {
            var a = 5/1

            var welcome = getString(R.string.text_welcome) + "value $a"

            var text = welcome + " " + etName.text.toString() + " " + Date(System.currentTimeMillis()).toString()

            tvData.text = text

            Log.d("MainActivity", "text is: $text")

            //Toast.makeText(this@MainActivity,
            //    text,
            //    Toast.LENGTH_LONG).show()


            Snackbar.make(layoutMain,
                text, Snackbar.LENGTH_LONG).show()
        }
    }
}
