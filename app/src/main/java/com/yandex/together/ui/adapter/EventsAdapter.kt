package com.yandex.together.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.yandex.together.R
import com.yandex.together.databinding.ViewHolderEventCardBinding
import com.yandex.together.ui.vo.EventCardVO

class EventsAdapter(private val onEventClick: (EventCardVO) -> Unit) :
    PagingDataAdapter<EventCardVO, EventsViewHolder>(DataDifferntiator) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_event_card, parent, false)
        return EventsViewHolder(view, onEventClick)
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object DataDifferntiator : DiffUtil.ItemCallback<EventCardVO>() {

        override fun areItemsTheSame(oldItem: EventCardVO, newItem: EventCardVO): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: EventCardVO, newItem: EventCardVO): Boolean {
            return oldItem == newItem
        }
    }
}

class EventsViewHolder(view: View, private val onEventClick: (EventCardVO) -> Unit) :
    RecyclerView.ViewHolder(view) {

    private val binding = ViewHolderEventCardBinding.bind(view)

    fun bind(event: EventCardVO?) {
        event?.apply {
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