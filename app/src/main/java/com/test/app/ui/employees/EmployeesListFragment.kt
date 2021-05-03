package com.test.app.ui.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.test.app.R
import com.test.app.data.network.Resource.Status.*
import com.test.app.databinding.FragmentListBinding
import com.test.app.domain.model.Employee
import com.test.app.ui.common.ItemClickListener
import com.test.app.ui.common.showErrorBar
import com.test.app.ui.common.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_list.*

@AndroidEntryPoint
class EmployeesListFragment : Fragment(), ItemClickListener {

    private val employeesListViewModel: EmployeesListViewModel by viewModels()
    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentListBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@EmployeesListFragment
            vm = this@EmployeesListFragment.employeesListViewModel
            clickListener = this@EmployeesListFragment
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupDivider()
        observeEmployees()
    }

    private fun setupDivider() {
        val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(requireContext(), R.drawable.divider)
            ?.let { itemDecorator.setDrawable(it) }
        employeesList.addItemDecoration(itemDecorator)
    }

    private fun observeEmployees() {
        employeesListViewModel.employees.observe(viewLifecycleOwner) {
            when (it.status) {
                LOADING -> binding.isLoading = true
                SUCCESS -> handleResponse(isEmpty = it.data?.employees?.size == 0)
                ERROR -> handleResponse(false, it.message)
            }
        }
    }

    private fun handleResponse(
        isSuccess: Boolean = true,
        msg: String? = null,
        isEmpty: Boolean = false
    ) {
        binding.isLoading = false

        if (isSuccess) {
            binding.isEmpty = isEmpty
        } else {
            showErrorBar(msg)
            binding.isEmpty = true
        }
    }

    override fun onItemClick(item: Any?) {
        if (item is Employee) {
            showToast(item.full_name)
            findNavController().navigate(EmployeesListFragmentDirections.listFragmentAction(item))
        }
    }
}