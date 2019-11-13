package hu.ait.animationdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var demoAnim = AnimationUtils.loadAnimation(
            this@MainActivity,
            R.anim.demo_anim)

        demoAnim.setAnimationListener(
            object: Animation.AnimationListener {
                override fun onAnimationRepeat(p0: Animation?) {

                }

                override fun onAnimationEnd(p0: Animation?) {
                    Toast.makeText(this@MainActivity, "ANIM READY", Toast.LENGTH_LONG).show()
                }

                override fun onAnimationStart(p0: Animation?) {
                }
            }
        )


        btnPlay.setOnClickListener {
            btnPlay.startAnimation(demoAnim)

            layoutMain.startAnimation(demoAnim)
        }
    }
}
