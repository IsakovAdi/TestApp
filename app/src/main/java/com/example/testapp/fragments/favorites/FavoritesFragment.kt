package com.example.testapp.fragments.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.domain.entity.CourseEntity
import com.example.testapp.R
import com.example.testapp.adapters.CourseDelegateAdapter
import com.example.testapp.databinding.FragmentFavoritesBinding
import com.livermor.delegateadapter.delegate.CompositeDelegateAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment(), CourseDelegateAdapter.FlagClickListener {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding
        get() = _binding ?: throw RuntimeException("FragmentFavoritesBinding == null")


    private val vm by viewModel<FavoritesFragmentViewModel>()

    private val adapter by lazy {
        CompositeDelegateAdapter(
            CourseDelegateAdapter(requireContext(), this@FavoritesFragment)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRv()
        vm.getCourses()
        observeVm()
    }

    private fun initRv() {
        binding.coursesRv.adapter = adapter
    }

    private fun observeVm() {
        vm.apply {
            coursesLiveData.observe(viewLifecycleOwner) {
                adapter.swapData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun flagClick(course: CourseEntity) {
        vm.deleteCourse(courseId = course.id)
    }

}