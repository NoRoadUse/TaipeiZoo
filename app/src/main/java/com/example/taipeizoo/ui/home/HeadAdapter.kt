package com.example.taipeizoo.ui.home

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.HeaderAnimalBinding
import com.example.taipeizoo.datamodel.SectionResult

class HeadAdapter : ListAdapter<SectionResult, HeadAdapter.ItemViewHolder>(
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

    inner class ItemViewHolder(private val binding: HeaderAnimalBinding, var viewType: Int) :
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
                startActivity(it.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val binding =
            HeaderAnimalBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding, viewType)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bindModel(getItem(position), position)
    }

    fun setOnItemClick(itemCallBack: ItemCallBack) {
        this.itemCallBackImpl = itemCallBack
    }

}