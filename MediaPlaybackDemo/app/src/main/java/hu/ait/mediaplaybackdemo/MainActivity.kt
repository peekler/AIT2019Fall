package hu.ait.mediaplaybackdemo

import android.media.MediaPlayer
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MediaPlayer.OnPreparedListener {

    lateinit var myPlayer: MediaPlayer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStart.setOnClickListener {
            //playRingtone()
            myPlayer = MediaPlayer.create(this@MainActivity,
                R.raw.demo)
            myPlayer.setOnPreparedListener(this@MainActivity)
        }

        btnStop.setOnClickListener {
            if (myPlayer != null) {
                //myPlayer.stop()
                myPlayer.seekTo(61000)
            }



        }

    }

    override fun onStop() {
        if (myPlayer != null) {
            myPlayer.stop()
        }

        super.onStop()
    }

    override fun onPrepared(p0: MediaPlayer?) {
        myPlayer.start()
    }

    fun playRingtone() {
        val uriNotif = RingtoneManager.getDefaultUri(
            RingtoneManager.TYPE_NOTIFICATION
        )
        val ringTone = RingtoneManager.getRingtone(
            applicationContext, uriNotif
        )
        ringTone.play()
    }


}
