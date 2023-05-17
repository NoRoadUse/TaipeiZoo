package com.example.taipeizoo.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.R
import com.example.taipeizoo.datamodel.SectionContent

class SectionAdapter : ListAdapter<SectionContent, SectionAdapter.ItemViewHolder>(
    DiffCallBack
) {

    private var itemCallBackImpl: ItemCallBack? = null

    interface ItemCallBack {
        fun onClick(data: SectionContent, position: Int)
    }

    object DiffCallBack : DiffUtil.ItemCallback<SectionContent>() {
        override fun areItemsTheSame(oldItem: SectionContent, newItem: SectionContent): Boolean {
            return oldItem._id == newItem._id
        }

        override fun areContentsTheSame(oldItem: SectionContent, newItem: SectionContent): Boolean {
            return oldItem == newItem
        }
    }

    inner class ItemViewHolder(itemView: View, var viewType: Int) :
        RecyclerView.ViewHolder(itemView) {

        private val imgSection: ImageView = itemView.findViewById(R.id.imgSection)
        private val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
        private val tvContent: AppCompatTextView = itemView.findViewById(R.id.tvContent)
        private val tvOpenTime: AppCompatTextView = itemView.findViewById(R.id.tvOpenTime)

        fun bindModel(data: SectionContent, position: Int) {

            Glide.with(itemView.context)
                .load(data.e_pic_url.replace("http", "https"))
                .centerCrop()
                .into(imgSection)

            tvTitle.text = data.e_name
            tvContent.text = data.e_info
            tvOpenTime.text = if (data.e_memo == "") "無休館資訊" else data.e_memo

            itemView.setOnClickListener {
                itemCallBackImpl?.onClick(data, position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_section, parent, false)

        return ItemViewHolder(view, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindModel(getItem(position), position)
    }

    fun setOnItemClick(itemCallBack: ItemCallBack) {
        this.itemCallBackImpl = itemCallBack
    }

}