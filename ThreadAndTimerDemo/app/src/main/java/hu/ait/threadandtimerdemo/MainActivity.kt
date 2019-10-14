package hu.ait.threadandtimerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var threadEnabled = false

    inner class MyAppendThread : Thread() {
        override fun run() {
            while(threadEnabled) {

                runOnUiThread {
                    tvData.append("&")
                }

                sleep(1000)
            }
        }
    }

    lateinit var timer: Timer

    inner class AppendTimerTask : TimerTask() {
        override fun run() {
            runOnUiThread {
                tvData.append("T")
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        timer = Timer()

        btnStart.setOnClickListener {
            //threadEnabled = true
            //MyAppendThread().start()
            //tvData.append("HELLO")

            timer.schedule(AppendTimerTask(), 3000, 2000)
        }

        btnStop.setOnClickListener {
            threadEnabled = false

            timer.cancel()
        }
    }

    override fun onStop() {
        super.onStop()
        threadEnabled = false

        timer.cancel()
    }
}
