package com.example.taipeizoo.ui.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.taipeizoo.R
import com.example.taipeizoo.databinding.FragmentNotificationsBinding
import com.example.taipeizoo.datamodel.AnimalResult

class AnimalFragment : Fragment() {

    companion object {
        const val ANIMAL = "animal"
    }

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val animalViewModel: AnimalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        arguments?.getSerializable(ANIMAL)?.let {
            animalViewModel.setSelectAnimal((it as AnimalResult))

        }

        animalViewModel.getSelectAnimal()?.apply {
            Glide.with(root)
                .load(formatAPic01Url)
                .into(binding.imgAnimal)

            binding.tvAnimal.text = getString(
                R.string.animal_desc,
                aNameCh,
                aNameLatin,
                aFeature,
                aBehavior,
                animalImportdate?.date?.subSequence(0..10)
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}