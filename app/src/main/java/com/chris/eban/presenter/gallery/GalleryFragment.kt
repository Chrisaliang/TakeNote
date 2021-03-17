package com.chris.eban.presenter.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.chris.eban.R
import com.chris.eban.common.EventListItemDecoration
import com.chris.eban.databinding.FragmentGalleryBinding
import com.chris.eban.presenter.BaseFragment

class GalleryFragment : BaseFragment() {

    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_gallery, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = GalleryAdapter()
        binding.rvViewList.layoutManager = LinearLayoutManager(view.context)
        binding.rvViewList.addItemDecoration(EventListItemDecoration())
        binding.rvViewList.adapter = adapter
    }
}