package hu.ait.aithello

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnShow.setOnClickListener {
            var text = etName.text.toString() + " " + Date(System.currentTimeMillis()).toString()

            tvData.text = text

            Toast.makeText(this@MainActivity,
                text,
                Toast.LENGTH_LONG).show()
        }
    }
}
