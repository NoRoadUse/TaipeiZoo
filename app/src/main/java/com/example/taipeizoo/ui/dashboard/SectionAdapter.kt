package com.example.taipeizoo.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.ItemSectionBinding
import com.example.taipeizoo.datamodel.SectionResultX

class SectionAdapter : ListAdapter<SectionResultX, SectionAdapter.ItemViewHolder>(
    DiffCallBack
) {

    private var itemCallBackImpl: ItemCallBack? = null

    interface ItemCallBack {
        fun onClick(data: SectionResultX, position: Int)
    }

    object DiffCallBack : DiffUtil.ItemCallback<SectionResultX>() {
        override fun areItemsTheSame(oldItem: SectionResultX, newItem: SectionResultX): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SectionResultX, newItem: SectionResultX): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(private val binding: ItemSectionBinding, var viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindModel(data: SectionResultX, position: Int) {

            Glide.with(itemView.context)
                .load(data.ePicUrl?.replace("http", "https"))
                .centerCrop()
                .into(binding.imgSection)

            binding.tvTitle.text = data.eName
            binding.tvContent.text = data.eInfo
            binding.tvOpenTime.text = if (data.eMemo == "") "無休館資訊" else data.eMemo

            itemView.setOnClickListener {
                itemCallBackImpl?.onClick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val bind = ItemSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(bind, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindModel(getItem(position), position)
    }

    fun setOnItemClick(itemCallBack: ItemCallBack) {
        this.itemCallBackImpl = itemCallBack
    }

}