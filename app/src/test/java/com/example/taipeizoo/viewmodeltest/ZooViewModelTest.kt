package com.example.taipeizoo.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.AnimalResultX
import com.example.taipeizoo.datamodel.SectionData
import com.example.taipeizoo.datamodel.SectionContent
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.repository.ZooRepo
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

class ZooViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val repo = mockk<ZooRepo>()
    private val vm = ZooViewModel(repo)


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
    fun testZooVmGetSectionIntro() {
        val testData = SectionData(SectionContent(count = 1))

        coEvery { repo.getZooSectionIntro() } returns testData

        runBlocking {
            vm.getZooSectionIntro()
        }

        coVerify(exactly = 1) { repo.getZooSectionIntro() }

        assertEquals(testData, vm.zooSection.value)
    }

    @Test
    fun testZooVmGetAnimalInfo() {
        val testData = listOf(AnimalResultX(aBehavior = "test", aLocation = "123"))
        val fakeResponse = AnimalData(AnimalResult(results = testData))

        coEvery { repo.getAnimalsInfo() } returns fakeResponse

        runBlocking {
            vm.getAnimalsInfo()
        }

        coVerify(exactly = 1) { repo.getAnimalsInfo() }

        assertEquals(testData, vm.getAnimals("123"))
    }

    @Test
    fun testZooViewModelSetSection() {
        val zooViewModel = ZooViewModel()

        val testData = SectionResult(eName = "Test Section")
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