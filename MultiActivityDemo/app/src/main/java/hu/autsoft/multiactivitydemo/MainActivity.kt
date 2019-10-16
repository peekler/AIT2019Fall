package hu.autsoft.multiactivitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {
        const val KEY_DETAILS = "KEY_DETAILS"

        const val KEY_TIME = "KEY_TIME"

        const val REQUEST_DETAILS = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            var intentDetails = Intent()
            intentDetails.setClass(this@MainActivity,
                DetailsActivity::class.java)

            intentDetails.putExtra(KEY_DETAILS, etName.text.toString())

            //startActivity(intentDetails)

            //DataHolder.name = etName.text.toString()

            startActivityForResult(intentDetails, REQUEST_DETAILS)

            //finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_DETAILS) {
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_LONG).show()
            } else if (resultCode == Activity.RESULT_OK) {

                var acceptTime = data?.getLongExtra(KEY_TIME, 0)

                Toast.makeText(this,
                    "ACCEPTED at: ${Date(acceptTime!!).toString()}",
                    Toast.LENGTH_LONG).show()
            }
        }
    }
}
