package hu.autsoft.multiactivitydemo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        //getIntent()

        var data = intent.getStringExtra(
            MainActivity.KEY_DETAILS)

        tvData.text = data
        title = DataHolder.name


        btnAccept.setOnClickListener {
            var intentResult = Intent()
            intentResult.putExtra(MainActivity.KEY_TIME,
                System.currentTimeMillis())

            setResult(Activity.RESULT_OK, intentResult)

            finish()
        }

        btnCancel.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)

            finish()
        }
    }

    override fun onBackPressed() {

        Toast.makeText(this, "YOU CAN NOT EXIT, YOU HAVE TO CHOOSE!",
            Toast.LENGTH_LONG).show()

    }
}
