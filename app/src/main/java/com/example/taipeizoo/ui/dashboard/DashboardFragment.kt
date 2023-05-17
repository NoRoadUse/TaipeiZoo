package com.example.taipeizoo.ui.dashboard

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentDashboardBinding
import com.example.taipeizoo.ui.component.DividerItemDecorator
import com.example.taipeizoo.viewmodel.ZooViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val adapter = SectionAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        dashboardViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        binding.rvSections.adapter = adapter
        binding.rvSections.addItemDecoration(
            DividerItemDecorator(
                ColorDrawable(
                    resources.getColor(
                        R.color.black, null
                    )
                )
            )
        )

        val zooViewModel = ViewModelProvider(this)[ZooViewModel::class.java]
        zooViewModel.getZooSectionIntro()
        zooViewModel.zooSection.observe(this, Observer {
            Log.e("test", it.toString())
            adapter.submitList(it.result.results)
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}