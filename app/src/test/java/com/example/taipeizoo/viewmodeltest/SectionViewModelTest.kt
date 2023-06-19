package com.example.taipeizoo.viewmodeltest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.taipeizoo.datamodel.AnimalContent
import com.example.taipeizoo.datamodel.AnimalData
import com.example.taipeizoo.datamodel.AnimalResult
import com.example.taipeizoo.datamodel.SectionResult
import com.example.taipeizoo.repository.ZooRepo
import com.example.taipeizoo.ui.section.SectionViewModel
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

class SectionViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val repo = mockk<ZooRepo>()
    private val vm = SectionViewModel(repo)


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
    fun testGetAnimalsInfo() {
        val testData =listOf(
            AnimalResult(
                aNameCh = "test 1",
                aLocation = "123"
            ),
            AnimalResult(
                aNameCh = "test 2",
                aLocation = "123"
            ),
            AnimalResult(
                aNameCh = "test 3",
                aLocation = "123"
            )
        )
        val fakeResult =
            Result.success(AnimalData(
                AnimalContent(
                    results = testData
                )
            ))

        coEvery { repo.getAnimalsInfo() } returns fakeResult

        runBlocking {
            vm.setSelectedSection(SectionResult(eName = "123"))
            vm.getAnimalsInfo()
        }

        coVerify(exactly = 1) { repo.getAnimalsInfo() }

        assertEquals(testData, vm.responseAnimal.value)
    }

}