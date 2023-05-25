package com.example.taipeizoo.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.taipeizoo.MainActivity
import com.example.taipeizoo.databinding.FragmentNotificationsBinding
import com.example.taipeizoo.viewmodel.ZooViewModel
import timber.log.Timber

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val zooViewModel: ZooViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root



        zooViewModel.getSelectAnimal()?.apply {
            Timber.d("$this")
            (activity as MainActivity).setToolBarTitle(aNameCh)

            Glide.with(root)
                .load(aPic01Url?.replace("http", "https"))
                .into(binding.imgAnimal)

            binding.tvAnimal.text =
                "${aNameCh}\n${aNameLatin}\n\n簡介\n${aFeature}\n\n行為\n${aBehavior}\n\n 最後更新: ${
                    animalImportdate?.date?.subSequence(0..10)
                }"
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}