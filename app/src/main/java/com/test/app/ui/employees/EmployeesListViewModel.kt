package com.test.app.ui.employees

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.app.data.network.Resource
import com.test.app.domain.model.Employee
import com.test.app.domain.usecases.EmployeesUseCase
import com.test.app.ui.common.ItemClickListener

class EmployeesListViewModel @ViewModelInject constructor(
    private val useCase: EmployeesUseCase
) : ViewModel(), ItemClickListener {

    var clickListener = MutableLiveData<Employee>()

    var employees = liveData {
        emit(Resource.loading())
        emit(useCase.employees())
    }

    override fun onItemClick(item: Any?) {
        if (item is Employee) clickListener.value = item
    }
}