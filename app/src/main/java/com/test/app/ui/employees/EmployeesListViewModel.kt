package com.test.app.ui.employees

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.EmployeesUseCase

class EmployeesListViewModel @ViewModelInject constructor(
    private val useCase: EmployeesUseCase
) : ViewModel() {

    private var emitLoading = true
    private val employeesLiveData = MutableLiveData(Unit)

    var employees = employeesLiveData.switchMap {
        fetchEmployees()
    }

    fun refresh() {
        emitLoading = false
        employeesLiveData.value = Unit
    }

    private fun fetchEmployees() = liveData {
        if (emitLoading) emit(Resource.loading())
        emit(useCase.employees())
    }
}