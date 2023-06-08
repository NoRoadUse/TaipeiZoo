package com.example.taipeizoo.ui.section

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.ItemSectionBinding
import com.example.taipeizoo.datamodel.AnimalResult

class AnimalAdapter : ListAdapter<AnimalResult, AnimalAdapter.ItemViewHolder>(
    DiffCallBack
) {

    private var itemCallBackImpl: ItemCallBack? = null

    interface ItemCallBack {
        fun onClick(data: AnimalResult, position: Int)
    }

    object DiffCallBack : DiffUtil.ItemCallback<AnimalResult>() {
        override fun areItemsTheSame(oldItem: AnimalResult, newItem: AnimalResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: AnimalResult, newItem: AnimalResult): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(private val binding: ItemSectionBinding, var viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindModel(data: AnimalResult, position: Int) {

            if (position == 0) {
                binding.tvHeader.visibility = View.VISIBLE
                binding.tvHeader.apply {
                    text = "動物資料"
                }
            }

            Glide.with(itemView.context)
                .load(data.formatAPic01Url)
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