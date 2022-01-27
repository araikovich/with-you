package com.yandex.together.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.yandex.together.R
import com.yandex.together.databinding.FragmentMainScreenBinding

class MainScreenFragment : Fragment() {

    private var binding: FragmentMainScreenBinding? = null
    private var adapter: EventsViewPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainScreenBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewPager()
    }

    private fun setupViewPager() {
        adapter = EventsViewPagerAdapter(childFragmentManager, lifecycle)
        binding?.viewPager?.adapter = adapter
        binding?.viewPager?.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 0) {
                    binding?.tvAllEvents?.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.title
                        )
                    )
                    binding?.tvMyEvents?.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.sub_title
                        )
                    )
                } else {
                    binding?.tvMyEvents?.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.title
                        )
                    )
                    binding?.tvAllEvents?.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.sub_title
                        )
                    )
                }
            }
        })
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }
}