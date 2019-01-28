package com.chris.eban.presenter.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.eban.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventListViewHolder> {

    private List<EventItem> data;

    EventListAdapter() {
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_event_list, parent, false);
        return new EventListViewHolder(view);
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
        private EventItem item;

        EventListViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bind(EventItem eventItem) {
            this.item = eventItem;
        }
    }
}
