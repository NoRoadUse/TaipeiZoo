package com.example.taipeizoo.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentHomeBinding
import com.example.taipeizoo.datamodel.AnimalResultX
import com.example.taipeizoo.viewmodel.ZooViewModel
import timber.log.Timber

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
        Timber.d("$section")

        section?.let { content ->
            Glide.with(binding.root)
                .load(section.ePicUrl?.replace("http", "https"))
                .into(binding.imgSection)

            binding.tvSectionContent.text = content.eInfo
            binding.tvSectionInfo.text =
                "${if (content.eMemo == "") "無休館資訊" else content.eMemo}\n${content.eCategory}"
            binding.tvSectionLink.setOnClickListener {
                Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse(content.eUrl)
                    startActivity(this)
                }
            }
        }

        binding.rvAnimal.adapter = adapter
        adapter.setOnItemClick(object : AnimalAdapter.ItemCallBack {
            override fun onClick(data: AnimalResultX, position: Int) {
                zooViewModel.setAnimal(data)
                findNavController().navigate(R.id.action_navigation_home_to_navigation_notifications)
            }
        })
        zooViewModel.getAnimals(section?.eName ?: "")?.takeIf { it.isNotEmpty() }?.apply {
            binding.tvAnimal.visibility = View.VISIBLE
            adapter.submitList(this)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}