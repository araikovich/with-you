package com.yandex.together.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.yandex.together.R
import com.yandex.together.databinding.ViewHolderEventCardBinding
import com.yandex.together.ui.vo.EventCardVO

class EventsAdapter(private val onEventClick: (EventCardVO) -> Unit) :
    RecyclerView.Adapter<EventsViewHolder>() {

    private val eventCards = mutableListOf<EventCardVO>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_event_card, parent, false)
        return EventsViewHolder(view, onEventClick)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(eventCards[position])
    }

    override fun getItemCount() = eventCards.size

    fun provideItems(events: List<EventCardVO>) {
        eventCards.clear()
        eventCards.addAll(events)
        notifyDataSetChanged()
    }
}

class EventsViewHolder(view: View, private val onEventClick: (EventCardVO) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val binding = ViewHolderEventCardBinding.bind(view)

    fun bind(event: EventCardVO) {
        event.apply {
            binding.categoryIcon.setBackgroundResource(categoryRes)
            binding.tvCategoryTitle.text = categoryTitle
            binding.tvTitle.text = title
            binding.tvDescription.text = description
            binding.tvPersonsCount.text = binding.root.context.getString(
                R.string.persons_count,
                currentPersonCount,
                totalPersonsCount
            )
            binding.tvDate.text = date
            binding.root.setOnClickListener {
                onEventClick(event)
            }
        }
    }
}