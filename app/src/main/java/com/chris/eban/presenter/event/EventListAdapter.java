package com.chris.eban.presenter.event;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.eban.R;
import com.chris.eban.databinding.ItemEventListBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import static com.chris.eban.presenter.event.EventDetailActivity.PAGE_ITEM;
import static com.chris.eban.presenter.event.EventDetailActivity.PAGE_STATUS;
import static com.chris.eban.presenter.event.EventDetailActivity.PAGE_STATUS_SAVE;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    private List<EventItem> data;

    EventListAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEventListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.item_event_list, parent, false);
        return new EventListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EventListViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    void updateAll(List<EventItem> items) {
        data.clear();
        data.addAll(items);
        notifyDataSetChanged();
    }

    void insertFirst(EventItem item) {
        data.add(0, item);
        notifyItemInserted(0);
    }

     EventItem removeItem(int position) {
        notifyItemRemoved(position);
         return data.remove(position);
     }

    class EventListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ItemEventListBinding binding;
        private EventItem item;

        EventListViewHolder(ItemEventListBinding binding) {
            super(binding.getRoot());
            binding.getRoot().setOnClickListener(this);
            this.binding = binding;
        }

        void bind(EventItem eventItem) {
            binding.setItem(eventItem);
            item = eventItem;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), EventDetailActivity.class);
            intent.putExtra(PAGE_STATUS, PAGE_STATUS_SAVE);
            Bundle extras = new Bundle();
            extras.putParcelable(PAGE_ITEM, item);
            intent.putExtras(extras);
            v.getContext().startActivity(intent);
        }
    }
}
