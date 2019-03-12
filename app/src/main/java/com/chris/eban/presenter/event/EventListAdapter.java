package com.chris.eban.presenter.event;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.chris.eban.R;
import com.chris.eban.databinding.ItemEventListBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

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

    class EventListViewHolder extends RecyclerView.ViewHolder {
        private ItemEventListBinding binding;

        EventListViewHolder(ItemEventListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(EventItem eventItem) {
            binding.setItem(eventItem);
        }
    }
}
