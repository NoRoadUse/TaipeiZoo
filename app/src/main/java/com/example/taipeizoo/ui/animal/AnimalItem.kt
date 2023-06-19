package com.example.taipeizoo.ui.animal

import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionResult

sealed class AnimalItem {
    abstract val headData: SectionResult?
    abstract val animalResult: AnimalResult?

    class Header(
        override val headData: SectionResult,
        override val animalResult: AnimalResult? = null
    ) : AnimalItem()

    class Data(
        override val headData: SectionResult? = null,
        override val animalResult: AnimalResult?
    ) : AnimalItem()
}
