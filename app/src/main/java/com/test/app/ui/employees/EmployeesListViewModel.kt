package com.test.app.ui.employees

import androidx.lifecycle.*
import com.test.app.data.network.Resource
import com.test.app.domain.usecases.EmployeesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeesListViewModel @Inject constructor(
    private val useCase: EmployeesUseCase
) : ViewModel() {

    private var emitLoading = true
    private val remoteEmployeesLiveData = MutableLiveData(Unit)

    var employees = useCase.employees().asLiveData()

    var remoteEmployees = remoteEmployeesLiveData.switchMap {
        remoteEmployees()
    }

    fun refresh() {
        emitLoading = false
        remoteEmployeesLiveData.value = Unit
    }

    private fun remoteEmployees() = liveData {
        if (emitLoading) emit(Resource.loading())
        emit(useCase.remoteEmployees())
    }
}