package com.chris.eban.presenter.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chris.eban.R;
import com.chris.eban.databinding.FragmentEventListBinding;
import com.chris.eban.presenter.BaseFragment;

import java.util.List;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class EventListFragment extends BaseFragment {

    private static final String TAG = "EventListFragment";
    private FragmentEventListBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    private EventListAdapter eventListAdapter;
    private EventListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = ViewModelProviders.of(this, factory).get(EventListViewModel.class);
        Timber.tag(TAG).d("onActivityCreated: %s", viewModel);
        binding.setEvent(viewModel);

        viewModel.init();

        viewModel.getEventList().observe(this, new Observer<List<EventItem>>() {
            @Override
            public void onChanged(List<EventItem> items) {
                eventListAdapter.updateAll(items);
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallBack());
        itemTouchHelper.attachToRecyclerView(binding.listEvent);
        eventListAdapter = new EventListAdapter();
        binding.listEvent.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.listEvent.addItemDecoration(
                new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        binding.listEvent.setAdapter(eventListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        viewModel.query();
    }

    class ItemTouchHelperCallBack extends ItemTouchHelper.Callback {

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView,
                              @NonNull RecyclerView.ViewHolder viewHolder,
                              @NonNull RecyclerView.ViewHolder target) {

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            if (direction == ItemTouchHelper.LEFT) {
                Toast.makeText(binding.getRoot().getContext(), "to left", Toast.LENGTH_SHORT).show();
                EventItem item = eventListAdapter.removeItem(viewHolder.getAdapterPosition());
                Timber.tag(TAG).d("item :%s is removed", item);
                viewModel.removeItem(item);
            } else {
                Toast.makeText(binding.getRoot().getContext(), "to right", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
