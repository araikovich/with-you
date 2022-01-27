package com.yandex.together.ui.all_events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.yandex.together.databinding.FragmentWithEventsBinding
import com.yandex.together.ui.EventCardsItemDecorator
import com.yandex.together.ui.EventsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllEventsFragment : Fragment() {

    private var binding: FragmentWithEventsBinding? = null
    private val viewModel: AllEventsViewModel by viewModel()
    private var adapter: EventsAdapter = EventsAdapter()

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
        viewModel.getEvents()
    }

    private fun observeItems() {
        viewModel.eventsLiveData.observe(viewLifecycleOwner, {
            adapter.provideItems(it)
        })
    }

    private fun setupAdapter() {
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