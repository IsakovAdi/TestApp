package com.example.testapp.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.domain.entity.CourseEntity
import com.example.testapp.adapters.CourseDelegateAdapter
import com.example.testapp.databinding.FragmentHomeBinding
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), CourseDelegateAdapter.FlagClickListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding == null")

    private val viewModel by viewModel<HomeFragmentViewModel>()

    private val adapter by lazy {
        CompositeDelegateAdapter(
            CourseDelegateAdapter(requireContext(), this@HomeFragment)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        binding.apply {
            sortTv.setOnClickListener {
                viewModel.sortClicked()
                try {
                    coursesRv.scrollToPosition(0)
                } catch (e: Exception) {
                    throw RuntimeException("Rv items is empty")
                }
            }
        }
        if (viewModel.coursesLiveData.value.isNullOrEmpty()) {
            viewModel.getAllCourses()
        }
        observeLiveData()
    }

    private fun initRv() {
        binding.coursesRv.adapter = adapter
    }

    private fun observeLiveData() {
        viewModel.apply {
            coursesLiveData.observe(viewLifecycleOwner) {
                adapter.swapData(it)
            }
            isLoading.observe(viewLifecycleOwner) {
                binding.progressBar.isVisible = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun flagClick(course: CourseEntity) {
        viewModel.courseHasLikeFlagClicked(course)
    }
}