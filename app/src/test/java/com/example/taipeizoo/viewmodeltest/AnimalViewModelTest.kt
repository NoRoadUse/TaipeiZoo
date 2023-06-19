package com.example.taipeizoo.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalContent
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.datamodel.SectionContent
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.repository.ZooRepo
import com.example.taipeizoo.ui.animal.AnimalViewModel
import com.example.taipeizoo.ui.portal.PortalViewModel
import com.example.taipeizoo.viewmodel.ZooViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class AnimalViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val vm = AnimalViewModel()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun testGetZooSectionIntro() {
        val testData =
            AnimalResult(aNameCh = "test")

        runBlocking {
            vm.setSelectAnimal(AnimalResult(aNameCh = "test"))
        }

        assertEquals(testData, vm.getSelectAnimal())
    }

}