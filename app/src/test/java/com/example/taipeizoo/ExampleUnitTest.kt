package com.example.taipeizoo

import com.example.taipeizoo.datamodel.AnimalResultX
import com.example.taipeizoo.datamodel.SectionResultX
import com.example.taipeizoo.viewmodel.ZooViewModel
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testZooViewModelSetSection() {
        val zooViewModel = ZooViewModel()

        val testData = SectionResultX(eName = "Test Section")
        zooViewModel.setSection(testData)

        assertEquals(testData, zooViewModel.getSelectSection())
    }

    @Test
    fun testZooViewModelSetAnimal() {
        val zooViewModel = ZooViewModel()
        val testData = AnimalResultX(aNameCh = "Test Animal")
        zooViewModel.setAnimal(testData)

        assertEquals(testData, zooViewModel.getSelectAnimal())
    }
}