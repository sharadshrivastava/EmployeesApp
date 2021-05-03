package com.test.app.ui.employees

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.test.app.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EmployeeDetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<EmployeeDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentDetailsBinding.inflate(inflater, container, false).apply {
            binding = this
            lifecycleOwner = this@EmployeeDetailsFragment
            employeeItem = args.item
        }.root
    }
}