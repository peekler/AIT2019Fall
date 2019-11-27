package hu.ait.android.httpmoneydemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import com.google.gson.Gson
import hu.ait.android.httpmoneydemo.asynctask.HttpGetTask
import hu.ait.android.httpmoneydemo.data.MoneyResult
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private val URL_BASE = "https://api.exchangeratesapi.io/latest?base=USD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRates.setOnClickListener {
            HttpGetTask(applicationContext).execute(URL_BASE)
        }
    }


    override fun onResume() {
        super.onResume()
        LocalBroadcastManager.getInstance(this).registerReceiver(
            brWeatherReceiver,
            IntentFilter(HttpGetTask.FILTER_RESULT)
        )
    }

    override fun onPause() {
        super.onPause()
        LocalBroadcastManager.getInstance(
            this
        ).unregisterReceiver(brWeatherReceiver)
    }

    private val brWeatherReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            val rawResult = intent.getStringExtra(
                HttpGetTask.KEY_RESULT
            )

            try {
                //tvResult.text = rawResult

                // V1 - Using JSONObject
                //val hufValue = JSONObject(rawResult).getJSONObject("rates").getString("HUF")
                //tvResult.text = hufValue

                // V2 - Using GSON
                var gson = Gson()
                var moneyResult = gson.fromJson(rawResult, MoneyResult::class.java)
                tvResult.text = "${moneyResult?.rates?.HUF} ${moneyResult?.rates?.GBP}"

            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }
    }
}
