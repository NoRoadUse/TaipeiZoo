package com.example.taipeizoo.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentHomeBinding
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.viewmodel.ZooViewModel
import timber.log.Timber

class SectionFragment : Fragment() {

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val section = zooViewModel.getSelectSection()
        Timber.d("$section")

        binding.rvAnimal.adapter = adapter
        adapter.setOnItemClick(object : AnimalAdapter.ItemCallBack {
            override fun onClick(data: AnimalResult, position: Int) {
                zooViewModel.setAnimal(data)
                findNavController().navigate(
                    R.id.action_navigation_section_to_navigation_animal,
                    bundleOf("title" to data.aNameCh)
                )
            }
        })

        zooViewModel.parseShowDataContent()

        zooViewModel.formatContentList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}