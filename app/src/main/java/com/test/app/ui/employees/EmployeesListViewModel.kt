package com.test.app.ui.employees

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.test.app.data.network.Resource
import com.test.app.domain.model.ApiResponse
import com.test.app.domain.usecases.EmployeesUseCase
import com.test.app.ui.common.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EmployeesListViewModel @Inject constructor(
    private val useCase: EmployeesUseCase
) : ViewModel() {

    private var emitLoading = true
    private val employeesLiveData = MutableLiveData(Unit)
    val viewState = MutableLiveData<ViewState>()

    var employees = employeesLiveData.switchMap {
        fetchEmployees()
    }

    init {
        viewState.value = ViewState()
    }

    private fun fetchEmployees() = liveData {
        if (emitLoading) emit(Resource.loading())
        emit(useCase.employees())
    }

    fun handleEmployeesResponse(resource: Resource<ApiResponse?>) {
        when (resource.status) {
            Resource.Status.LOADING -> viewState.value = currentViewState().copy(loading = true)
            Resource.Status.SUCCESS -> handleResponse(isEmpty = resource.data?.employees?.size == 0)
            Resource.Status.ERROR -> handleResponse(false, resource.message)
        }
    }

    private fun handleResponse(
        isSuccess: Boolean = true,
        msg: String? = null,
        isEmpty: Boolean = false
    ) {
        if (isSuccess) {
            viewState.value =
                currentViewState().copy(loading = false, empty = isEmpty, error = false)
        } else {
            viewState.value =
                currentViewState().copy(loading = false, empty = true, error = true, errorMsg = msg)
        }
    }

    private fun currentViewState() = viewState.value!!

    fun refresh() {
        emitLoading = false
        employeesLiveData.value = Unit
    }
}