package com.chris.eban.presenter.gallery

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.chris.eban.R
import com.chris.eban.databinding.ItemSlideGalleryBinding

class GalleryAdapter : RecyclerView.Adapter<GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding = DataBindingUtil.inflate<ItemSlideGalleryBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_slide_gallery,
                parent, false
        )
        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bind()
    }

}


class GalleryViewHolder(binding: ItemSlideGalleryBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind() {}
}
