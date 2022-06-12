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

    private val remoteEmployeesLiveData = MutableLiveData(Unit)

    var employees = useCase.employees()?.asLiveData()

    //using switchMap to avoid observing two live data for refresh functionality in Data-Binding scenarios.
    var remoteEmployees = remoteEmployeesLiveData.switchMap {
        remoteEmployees()
    }

    fun refresh() {
        remoteEmployeesLiveData.value = Unit
    }

    //For pull to refresh
    private fun remoteEmployees() = liveData {
        if (useCase.isDBEmpty()) emit(Resource.loading())
        emit(useCase.remoteEmployees())
    }
}