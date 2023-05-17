package com.example.taipeizoo

import com.example.taipeizoo.datamodel.AnimalContent
import com.example.taipeizoo.datamodel.SectionContent
import com.example.taipeizoo.viewmodel.ZooViewModel
import org.junit.Test

import org.junit.Assert.*

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
        val testData = SectionContent(
            _id = UInt.MAX_VALUE,
            _importdate = hashMapOf("test" to "value"),
            e_no = UInt.MAX_VALUE,
            e_category = "test",
            e_name = "test",
            e_pic_url = "test",
            e_info = "test",
            e_memo = "test",
            e_geo = "test",
            e_url = "test"
        )
        zooViewModel.setSection(testData)

        assertEquals(testData, zooViewModel.getSelectSection())
    }

    @Test
    fun testZooViewModelSetAnimal() {
        val zooViewModel = ZooViewModel()
        val testData = AnimalContent(
            _id = UInt.MAX_VALUE,
            _importdate = hashMapOf("test" to "value"),
            a_name_ch = "test",
            a_summary = "test",
            a_keywords = "test",
            a_alsoknown = "test",
            a_geo = "test",
            a_location = "test",
            a_poigroup = "test",
            a_name_en = "test",
            a_name_latin = "test",
            a_phylum = "test",
            a_class = "test",
            a_order = "test",
            a_family = "test",
            a_conservation = "test",
            a_distribution = "test",
            a_habitat = "test",
            a_feature = "test",
            a_behavior = "test",
            a_diet = "test",
            a_crisis = "test",
            a_interpretation = "test",
            a_theme_name = "test",
            a_theme_url = "test",
            a_adopt = "test",
            a_code = "test",
            a_pic01_alt = "test",
            a_pic01_url = "test",
            a_pic02_alt = "test",
            a_pic02_url = "test",
            a_pic03_alt = "test",
            a_pic03_url = "test",
            a_pic04_alt = "test",
            a_pic04_url = "test",
            a_pdf01_alt = "test",
            a_pdf01_url = "test",
            a_pdf02_alt = "test",
            a_pdf02_url = "test",
            a_voice01_alt = "test",
            a_voice01_url = "test",
            a_voice02_alt = "test",
            a_voice02_url = "test",
            a_voice03_alt = "test",
            a_voice03_url = "test",
            a_vedio_url = "test",
            a_update = "test",
            a_cid = "test"
        )
        zooViewModel.setAnimal(testData)

        assertEquals(testData, zooViewModel.getSelectAnimal())
    }
}