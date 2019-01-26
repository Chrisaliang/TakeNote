package com.chris.eban.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chris.eban.R
import com.chris.eban.databinding.FragmentMainBinding
import kotlinx.android.synthetic.main.fragment_main.*
import timber.log.Timber
import javax.inject.Inject

class MainFragment : BaseFragment() {
    private var TAG = "MainFragment"

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel: MainViewModel
//    @Inject
//    internal var factory: ViewModelProvider.Factory? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        Timber.tag(TAG).d("create main fragment")
        binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_main, container, false)
        binding.setLifecycleOwner(this)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.eBan = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.tag(TAG).d("main fragment view created and show")

        recyclerViewMain.layoutManager = LinearLayoutManager(view.context)
        recyclerViewMain.addItemDecoration(
                DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL))
        recyclerViewMain.adapter = MainAdapter()

    }


    class MainViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    class MainAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.main_item_main, parent, false)
            return MainViewHolder(itemView)
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//           if (holder is MainViewHolder) holder.bind()
        }
    }

}
