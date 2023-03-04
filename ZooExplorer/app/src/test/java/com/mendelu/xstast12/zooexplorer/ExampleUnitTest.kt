package com.mendelu.xstast12.zooexplorer

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.mendelu.xstast12.zooexplorer.di.test.provideInternalStorageRepositoryTest
import com.mendelu.xstast12.zooexplorer.di.test.provideLocalRepositoryTest
import com.mendelu.xstast12.zooexplorer.ui.screens.detail.DetailScreenViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.rules.TestRule


class ExampleUnitTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val dispatcher = StandardTestDispatcher()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun getImage() {
        Dispatchers.setMain(dispatcher)
        val viewModel = DetailScreenViewModel(provideLocalRepositoryTest(), provideInternalStorageRepositoryTest())
        viewModel.animalId = 1
        viewModel.fetchAnimal()
        assertNull(viewModel.animalImage)

    }


}