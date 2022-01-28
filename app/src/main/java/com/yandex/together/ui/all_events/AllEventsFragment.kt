package com.yandex.together.ui.all_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandex.together.databinding.FragmentWithEventsBinding
import com.yandex.together.ui.adapter.EventCardsItemDecorator
import com.yandex.together.ui.EventsAdapter
import com.yandex.together.ui.MainActivity
import com.yandex.together.ui.event_detail.EventDetailBottomSheetDialogFragment
import com.yandex.together.ui.event_detail.EventDetailVO
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllEventsFragment : Fragment() {

    private var binding: FragmentWithEventsBinding? = null
    private val viewModel: AllEventsViewModel by viewModel()
    private var adapter: EventsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWithEventsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapter()
        observeItems()
    }

    private fun observeItems() {
        viewModel.listData.observe(viewLifecycleOwner, {
            lifecycleScope.launch {  adapter?.submitData(it) }
        })
    }

    private fun setupAdapter() {
        adapter = EventsAdapter {
            EventDetailBottomSheetDialogFragment().apply {
                arguments = bundleOf("vo" to EventDetailVO(
                    it.title,
                    it.description,
                    it.currentPersonCount,
                    it.totalPersonsCount,
                    it.chatLink
                ))
            }.show(childFragmentManager, "TAG")
        }
        binding?.rvAllEvents?.adapter = adapter
        binding?.rvAllEvents?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.rvAllEvents?.addItemDecoration(EventCardsItemDecorator())
        binding?.root?.postDelayed({adapter?.refresh()}, 1000)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

    companion object {

        fun createInstance(): AllEventsFragment {
            return AllEventsFragment()
        }
    }
}