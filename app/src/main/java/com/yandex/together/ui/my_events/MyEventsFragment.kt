package com.yandex.together.ui.my_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandex.together.databinding.FragmentWithEventsBinding
import com.yandex.together.ui.adapter.EventCardsItemDecorator
import com.yandex.together.ui.EventsAdapter
import com.yandex.together.ui.all_events.AllEventsFragment
import com.yandex.together.ui.event_detail.EventDetailBottomSheetDialogFragment
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyEventsFragment : Fragment() {

    private var binding: FragmentWithEventsBinding? = null
    private val viewModel: MyEventsViewModel by viewModel()
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
        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            //lifecycleScope.launch {  adapter?.submitData(it) }
        })
    }

    private fun setupAdapter() {
        adapter = EventsAdapter {
            EventDetailBottomSheetDialogFragment().show(childFragmentManager, "TAG")
        }
        binding?.rvAllEvents?.adapter = adapter
        binding?.rvAllEvents?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.rvAllEvents?.addItemDecoration(EventCardsItemDecorator())
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