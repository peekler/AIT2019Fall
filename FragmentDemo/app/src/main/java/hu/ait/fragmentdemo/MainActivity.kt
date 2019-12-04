package hu.ait.fragmentdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener(myOnNavigationItemSelectedListener)


        showFragmentByTag("TAG_MAIN", false)
    }


    private val myOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_main -> {
                showFragmentByTag("TAG_MAIN", true)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_transactions -> {
                showFragmentByTag("TAG_TRANSACTIONS", true)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun showFragmentByTag(tag: String,
                                  toBackStack: Boolean) {
        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(tag)
        if (fragment == null) {
            if (tag == "TAG_MAIN") {
                fragment = FragmentMain()
            } else if (tag == "TAG_TRANSACTIONS") {
                fragment = FragmentTransaction()
            }
        }

        if (fragment != null) {
            val ft = supportFragmentManager
                .beginTransaction()
            ft.replace(R.id.layoutContainer, fragment!!, tag)
            if (toBackStack) {
                ft.addToBackStack(null)
            }
            ft.commit()
        }
    }
}
