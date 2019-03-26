package com.chris.eban.presenter.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chris.eban.BR;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import timber.log.Timber;

public class EventListFragment extends BaseFragment {

    private static final String TAG = "EventListFragment";
    private FragmentEventListBinding binding;
    @Inject
    ViewModelProvider.Factory factory;
    private EventListAdapter eventListAdapter;

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
        EventListViewModel viewModel = ViewModelProviders.of(this, factory).get(EventListViewModel.class);
        Timber.tag(TAG).d("onActivityCreated: %s", viewModel);
        binding.setVariable(BR.event, viewModel);

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
        eventListAdapter = new EventListAdapter();
        RecyclerView list = view.findViewById(R.id.list_event);
        list.setLayoutManager(new LinearLayoutManager(view.getContext()));
        list.addItemDecoration(new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL));
        list.setAdapter(eventListAdapter);
    }
}
