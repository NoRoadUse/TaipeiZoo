package com.example.taipeizoo.ui.portal

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.ItemSectionBinding
import com.example.taipeizoo.datamodel.SectionResult

class SectionAdapter : ListAdapter<SectionResult, SectionAdapter.ItemViewHolder>(
    DiffCallBack
) {

    private var itemCallBackImpl: ItemCallBack? = null

    interface ItemCallBack {
        fun onClick(data: SectionResult, position: Int)
    }

    object DiffCallBack : DiffUtil.ItemCallback<SectionResult>() {
        override fun areItemsTheSame(oldItem: SectionResult, newItem: SectionResult): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SectionResult, newItem: SectionResult): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(private val binding: ItemSectionBinding, var viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindModel(data: SectionResult, position: Int) {

            Glide.with(itemView.context)
                .load(data.formatEPicUrl)
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