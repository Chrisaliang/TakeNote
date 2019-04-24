package com.chris.eban.presenter.event

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris.eban.R
import com.chris.eban.databinding.ItemEventListBinding
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_ITEM
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_STATUS
import com.chris.eban.presenter.event.EventDetailActivity.Companion.PAGE_STATUS_SAVE
import java.util.*

class EventListAdapter internal constructor() : RecyclerView.Adapter<EventListAdapter.EventListViewHolder>() {

    private val data: MutableList<EventItem>

    init {
        data = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        val binding = DataBindingUtil.inflate<ItemEventListBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_event_list, parent, false)
        return EventListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun updateAll(items: List<EventItem>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    fun insertFirst(item: EventItem) {
        data.add(0, item)
        notifyItemInserted(0)
    }

    fun removeItem(position: Int): EventItem {
        notifyItemRemoved(position)
        return data.removeAt(position)
    }

    inner class EventListViewHolder(private val binding: ItemEventListBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private var item: EventItem? = null

        init {
            binding.root.setOnClickListener(this)
        }

        fun bind(eventItem: EventItem) {
            binding.item = eventItem
            item = eventItem
        }

        override fun onClick(v: View) {
            val intent = Intent(v.context, EventDetailActivity::class.java)
            intent.putExtra(PAGE_STATUS, PAGE_STATUS_SAVE)
            val extras = Bundle()
            extras.putParcelable(PAGE_ITEM, item)
            intent.putExtras(extras)
            v.context.startActivity(intent)
        }
    }
}
