package com.yandex.together.ui.main_screen

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yandex.together.ui.all_events.AllEventsFragment
import com.yandex.together.ui.my_events.MyEventsFragment

class EventsViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllEventsFragment.createInstance()
            1 -> MyEventsFragment.createInstance()
            else -> AllEventsFragment.createInstance()
        }
    }

    companion object {
        private const val ITEMS_COUNT = 2
    }
}