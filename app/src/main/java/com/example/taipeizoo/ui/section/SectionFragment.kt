package com.example.taipeizoo.ui.section

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentHomeBinding
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.ui.animal.AnimalFragment.Companion.ANIMAL
import com.example.taipeizoo.viewmodel.ZooViewModel
import timber.log.Timber

class SectionFragment : Fragment() {

    companion object {
            const val SELECTED_SECTION = "selected_section"
    }

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val sectionViewModel: SectionViewModel by viewModels()

    private val adapter = AnimalAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.getSerializable(SELECTED_SECTION)?.let {
            sectionViewModel.setSelectedSection(it as SectionResult)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        sectionViewModel.responseAnimal.observe(viewLifecycleOwner) {
            sectionViewModel.parseShowDataContent(it)
        }

        sectionViewModel.formatContentList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.rvAnimal.adapter = adapter
        adapter.setOnItemClick(object : AnimalAdapter.ItemCallBack {
            override fun onClick(data: AnimalResult, position: Int) {
                findNavController().navigate(
                    R.id.action_navigation_section_to_navigation_animal,
                    bundleOf("title" to data.aNameCh, ANIMAL to data)
                )
            }
        })


        sectionViewModel.getAnimalsInfo()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}