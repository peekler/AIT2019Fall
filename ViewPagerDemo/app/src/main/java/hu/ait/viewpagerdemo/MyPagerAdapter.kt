package hu.ait.viewpagerdemo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class MyPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return if (position == 0) {
            FragmentOne()
        } else {
            FragmentTwo()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (position == 0) "Main page" else "Details page"
    }

    override fun getCount(): Int {
        return 2
    }


}