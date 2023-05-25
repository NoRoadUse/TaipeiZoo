package com.example.taipeizoo.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.ItemSectionBinding
import com.example.taipeizoo.datamodel.AnimalResultX

class AnimalAdapter : ListAdapter<AnimalResultX, AnimalAdapter.ItemViewHolder>(
    DiffCallBack
) {

    private var itemCallBackImpl: ItemCallBack? = null

    interface ItemCallBack {
        fun onClick(data: AnimalResultX, position: Int)
    }

    object DiffCallBack : DiffUtil.ItemCallback<AnimalResultX>() {
        override fun areItemsTheSame(oldItem: AnimalResultX, newItem: AnimalResultX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnimalResultX, newItem: AnimalResultX): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(private val binding: ItemSectionBinding, var viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindModel(data: AnimalResultX, position: Int) {

            if (position == 0) {
                binding.tvHeader.visibility = View.VISIBLE
                binding.tvHeader.apply {
                    text = data.aNameCh
                }
            }

            Glide.with(itemView.context)
                .load(data.aPic01Url?.replace("http", "https"))
                .centerCrop()
                .into(binding.imgSection)

            binding.tvTitle.text = data.aNameCh
            binding.tvContent.text = data.aDistribution
            binding.tvOpenTime.visibility = View.GONE

            itemView.setOnClickListener {
                itemCallBackImpl?.onClick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindModel(getItem(position), position)
    }

    fun setOnItemClick(itemCallBack: ItemCallBack) {
        this.itemCallBackImpl = itemCallBack
    }

}