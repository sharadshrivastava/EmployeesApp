package com.test.app.ui.employees

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.test.app.R
import com.test.app.data.network.Resource
import com.test.app.domain.model.Employee
import com.test.app.domain.usecases.EmployeesUseCase

class EmployeesListViewModel @ViewModelInject constructor(
    private val useCase: EmployeesUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    var clickListener  = MutableLiveData<Employee>()
    var layoutIds = arrayOf(R.layout.layout_employee_item)

    var employees = liveData {
        emit(Resource.loading())
        emit(useCase.employees())
    }
}