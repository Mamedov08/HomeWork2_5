package com.example.homework2_5.taken

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.homework2_5.databinding.GalleryItemBinding
import com.example.homework2_5.ui.loadImage

class ImageAdapter : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {
    private val listImage = arrayListOf<ImageModel>()
    private lateinit var listener: OnItemClick

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            GalleryItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ),
                parent, false
            )
        )
    }

    fun setListener(onItemClick: OnItemClick) {
        listener = onItemClick
    }

    fun setImageList(img: List<ImageModel>) {
        listImage.clear()
        listImage.addAll(img)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.onBind(listImage[position], position)
    }

    override fun getItemCount() = listImage.size

    inner class ImageViewHolder(private val binding: GalleryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(imageModel: ImageModel, position: Int) {
            val loadImage = binding.imgGallery.loadImage(imageModel.image)

            binding.imgDone.isVisible = imageModel.isSelected
            itemView.setOnClickListener {
                listener.onClicked(imageModel, position)
            }
        }
    }

    interface OnItemClick {
        fun onClicked(list: ImageModel, position: Int)

    }


}