package com.example.taipeizoo.ui.dashboard

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentDashboardBinding
import com.example.taipeizoo.datamodel.SectionResultX
import com.example.taipeizoo.ui.component.DividerItemDecorator
import com.example.taipeizoo.viewmodel.ZooViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = SectionAdapter()

    private val zooViewModel: ZooViewModel by activityViewModels<ZooViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.rvSections.adapter = adapter
        adapter.setOnItemClick(object : SectionAdapter.ItemCallBack {
            override fun onClick(data: SectionResultX, position: Int) {
                Log.e("animal", "${zooViewModel.getAnimals(data.eName ?: "")}")
                zooViewModel.setSection(data)
                findNavController().navigate(R.id.action_navigation_dashboard_to_navigation_home2)
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

        zooViewModel.getZooSectionIntro()
        zooViewModel.getAnimalsInfo()

        zooViewModel.zooSection.observe(this, Observer {
            Log.e("test", it.toString())
            adapter.submitList(it.result?.results)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}