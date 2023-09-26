package com.example.my_media.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.my_media.R
import com.example.my_media.databinding.FragmentHomeBinding

class HomeFragment: Fragment() {
    companion object {
        fun newInstance() = HomeFragment()
    }
    private lateinit var adapter : HomeListAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels() { HomeViewModelFactory() }

    private val homeListAdapter by lazy {
        HomeListAdapter(
            itemClickListener = { item ->
                //Todo (VideoDetailFragment 로 데이터 전달)
            }
        )
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()

    }

    private fun initView() = with(binding) {
        recyclerViewPopular.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = homeListAdapter
        }
        recyclerViewSubscribe.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            adapter = homeListAdapter
        }

        when(chipGroup.checkedChipId) {
//            R.id.chip_travel -> ...
            // Todo (키워드 클릭 관련 처리)
        }
    }

    private fun initViewModel() = with(viewModel) {
        list.observe(viewLifecycleOwner) {
//            homeListAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
