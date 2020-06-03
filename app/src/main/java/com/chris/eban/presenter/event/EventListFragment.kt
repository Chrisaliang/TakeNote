package com.chris.eban.presenter.event

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chris.eban.R
import com.chris.eban.common.EventListItemDecoration
import com.chris.eban.databinding.FragmentEventListBinding
import com.chris.eban.presenter.BaseFragment
import com.google.android.material.appbar.AppBarLayout
import timber.log.Timber
import javax.inject.Inject

class EventListFragment : BaseFragment() {
    private lateinit var binding: FragmentEventListBinding

    @Inject
    lateinit var factory: ViewModelProvider.Factory
    private lateinit var eventListAdapter: EventListAdapter
    private lateinit var viewModel: EventListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_event_list, container, false)
        binding.lifecycleOwner = this

        val listener = AppBarLayout.OnOffsetChangedListener { appBarLayout: AppBarLayout, i: Int ->
            Timber.tag(TAG).e("offset is %d", i)
            if (i >= -210) {
                Timber.tag(TAG).e("background has changed!!!")
                binding.toolbar.title = ""
            } else {
                binding.toolbar.setTitle(R.string.app_name)
            }
        }
        binding.appBar.removeOnOffsetChangedListener(listener)
        binding.appBar.addOnOffsetChangedListener(listener)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this.viewModelStore, factory).get(EventListViewModel::class.java)
        Timber.tag(TAG).d("onActivityCreated: %s", viewModel)
        binding.event = viewModel

        viewModel.init()

        viewModel.eventList.observe(this.viewLifecycleOwner, Observer<List<EventItem>> { items ->
            eventListAdapter.updateAll(items)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemTouchHelper = ItemTouchHelper(ItemTouchHelperCallBack())
        itemTouchHelper.attachToRecyclerView(binding.listEvent)
        eventListAdapter = EventListAdapter()
        binding.listEvent.layoutManager = LinearLayoutManager(view.context)
        binding.listEvent.addItemDecoration(EventListItemDecoration())
        binding.listEvent.adapter = eventListAdapter

        binding.fab.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_event_create)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.queryChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main, menu)
    }

    internal inner class ItemTouchHelperCallBack : ItemTouchHelper.Callback() {

        override fun getMovementFlags(recyclerView: RecyclerView,
                                      viewHolder: RecyclerView.ViewHolder): Int {
            return makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
        }

        override fun onMove(recyclerView: RecyclerView,
                            viewHolder: RecyclerView.ViewHolder,
                            target: RecyclerView.ViewHolder): Boolean {

            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (direction == ItemTouchHelper.LEFT) {
                Toast.makeText(binding.root.context, "to left", Toast.LENGTH_SHORT).show()
                val item = eventListAdapter.removeItem(viewHolder.adapterPosition)
                Timber.tag(TAG).d("item :%s is removed", item)
                viewModel.removeItem(item)
            } else {
                Toast.makeText(binding.root.context, "to right", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {

        private const val TAG = "EventListFragment"
    }
}
