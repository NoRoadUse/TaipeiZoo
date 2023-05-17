package com.example.taipeizoo.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.taipeizoo.databinding.FragmentHomeBinding
import com.example.taipeizoo.viewmodel.ZooViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val zooViewModel: ZooViewModel by activityViewModels()

    private val adapter = AnimalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val section = zooViewModel.getSelectSection()
        Log.e("", "$section")

        section?.let { content ->
            Glide.with(binding.root)
                .load(section.e_pic_url.replace("http", "https"))
                .into(binding.imgSection)

            binding.tvSectionContent.text = content.e_info
            binding.tvSectionInfo.text = "${content.e_memo}\n${content.e_category}"
            binding.tvSectionLink.setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(content.e_url)
                    startActivity(this)
                }
            }
        }

        binding.rvAnimal.adapter = adapter

        adapter.submitList(zooViewModel.getAnimals(section?.e_name ?: ""))

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}