package com.chris.eban.presenter.slide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.chris.eban.R

class SlideShowFragment : Fragment() {

    companion object {
        fun newInstance() = SlideShowFragment()
    }

    private lateinit var viewModel: SlideShowViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.slide_show_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SlideShowViewModel::class.java)
        // TODO: Use the ViewModel
    }

}