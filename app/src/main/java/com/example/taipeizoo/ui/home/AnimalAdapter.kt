package com.example.taipeizoo.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.R
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

    inner class ItemViewHolder(itemView: View, var viewType: Int) :
        RecyclerView.ViewHolder(itemView) {

        private val imgSection: ImageView = itemView.findViewById(R.id.imgSection)
        private val tvTitle: AppCompatTextView = itemView.findViewById(R.id.tvTitle)
        private val tvContent: AppCompatTextView = itemView.findViewById(R.id.tvContent)
        private val tvOpenTime: AppCompatTextView = itemView.findViewById(R.id.tvOpenTime)

        fun bindModel(data: AnimalResultX, position: Int) {

            Glide.with(itemView.context)
                .load(data.aPic01Url?.replace("http", "https"))
                .centerCrop()
                .into(imgSection)

            tvTitle.text = data.aNameCh
            tvContent.text = data.aDistribution
            tvOpenTime.visibility = View.GONE

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