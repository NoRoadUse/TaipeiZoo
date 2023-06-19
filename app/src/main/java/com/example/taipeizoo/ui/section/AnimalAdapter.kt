package com.example.taipeizoo.ui.section

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.HeaderAnimalBinding
import com.example.taipeizoo.databinding.ItemSectionBinding
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.ui.animal.AnimalItem

class AnimalAdapter : ListAdapter<AnimalItem, RecyclerView.ViewHolder>(
    DiffCallBack
) {

    private var itemCallBackImpl: ItemCallBack? = null

    interface ItemCallBack {
        fun onClick(data: AnimalResult, position: Int)
    }

    object DiffCallBack : DiffUtil.ItemCallback<AnimalItem>() {
        override fun areItemsTheSame(oldItem: AnimalItem, newItem: AnimalItem): Boolean {
            return when (oldItem) {
                is AnimalItem.Header -> {
                    oldItem.headData == newItem.headData
                }

                is AnimalItem.Data -> {
                    oldItem.animalResult == newItem.animalResult
                }

                else -> {
                    false
                }
            }
        }

        override fun areContentsTheSame(oldItem: AnimalItem, newItem: AnimalItem): Boolean {
            return oldItem == newItem
        }
    }

    inner class HeaderItemViewHolder(private val binding: HeaderAnimalBinding, var viewType: Int) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindModel(data: SectionResult, position: Int) {

            Glide.with(binding.root)
                .load(data.formatEPicUrl)
                .into(binding.imgSection)

            binding.tvSectionContent.text = data.eInfo
            binding.tvSectionInfo.text =
                "${if (data.eMemo == "") "無休館資訊" else data.eMemo}\n${data.eCategory}"
            binding.tvSectionLink.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW).apply {
                    this.data = Uri.parse(data.eUrl)
                }
                ContextCompat.startActivity(it.context, intent, null)
            }
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

    override fun getItemViewType(position: Int): Int = when (getItem(position)) {
        is AnimalItem.Header -> {
            1
        }

        else -> {
            0
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            1 -> {
                HeaderItemViewHolder(
                    HeaderAnimalBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), viewType
                ) as RecyclerView.ViewHolder
            }

            else -> {
                ItemViewHolder(
                    ItemSectionBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), viewType
                ) as RecyclerView.ViewHolder
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)

        if (item is AnimalItem.Header) {
            item.headData.let {
                (holder as (HeaderItemViewHolder)).bindModel(it, position)
            }
        } else {
            item.animalResult?.let {
                (holder as (ItemViewHolder)).bindModel(it, position)
            }
        }
    }

    fun setOnItemClick(itemCallBack: ItemCallBack) {
        this.itemCallBackImpl = itemCallBack
    }

}