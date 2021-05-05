package com.test.app.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.test.app.common.employees
import com.test.app.common.readJson
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.EmployeesUseCase
import com.test.app.ui.employees.EmployeesListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EmployeesListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var employeesUseCase: EmployeesUseCase

    private lateinit var employeesViewModel: EmployeesListViewModel

    var employees = employees(readJson("response.json")) //Reading mock json

    @Before
    fun setup() {
        employeesViewModel = EmployeesListViewModel(employeesUseCase)
    }

    @Test
    fun testEmployees() {
        runBlocking {
            Mockito.`when`(employeesUseCase.remoteEmployees())
                .thenReturn(Resource.success(employees))
            Mockito.`when`(employeesUseCase.isDBEmpty()).thenReturn(true)

            employeesViewModel.remoteEmployees.observeForever {
                if (it.status == Resource.Status.SUCCESS) {
                    Assert.assertEquals(employees, it.data)
                }
            }
        }
    }
}