package com.example.taipeizoo.ui.portal

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentDashboardBinding
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.ui.component.DividerItemDecorator
import com.example.taipeizoo.ui.section.SectionFragment.Companion.SELECTED_SECTION
import com.example.taipeizoo.viewmodel.ZooViewModel
import timber.log.Timber

class PortalFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = SectionAdapter()

    private val zooViewModel: ZooViewModel by activityViewModels<ZooViewModel>()
    private val portalViewModel: PortalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(PortalViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvSections.adapter = adapter
        adapter.setOnItemClick(object : SectionAdapter.ItemCallBack {
            override fun onClick(data: SectionResult, position: Int) {
                findNavController().navigate(R.id.action_navigation_main_to_navigation_section, bundleOf("title" to data.eName, SELECTED_SECTION to data))
            }
        })
        binding.rvSections.addItemDecoration(
            DividerItemDecorator(
                ColorDrawable(
                    resources.getColor(
                        R.color.black, null
                    )
                )
            )
        )

        portalViewModel.getZooSectionIntro()

        portalViewModel.zooSection.observe(viewLifecycleOwner, Observer {
            Timber.d("test %s", it.toString())
            adapter.submitList(it.result?.results)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}