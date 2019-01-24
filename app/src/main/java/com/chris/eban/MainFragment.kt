package com.chris.eban

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
