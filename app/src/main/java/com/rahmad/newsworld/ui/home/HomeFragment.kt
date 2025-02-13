package com.rahmad.newsworld.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rahmad.newsworld.data.source.remote.retrofit.ApiResult
import com.rahmad.newsworld.databinding.FragmentHomeBinding
import com.rahmad.newsworld.domain.model.News
import com.rahmad.newsworld.ui.adapter.NewsAdapter
import com.rahmad.newsworld.utils.DataMappers.toEntity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        if (savedInstanceState == null) {
            observeNews("crypto")
        }
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSearchView()

    }

    private fun setupSearchView() {
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    observeNews(it)
                }
                binding?.searchView?.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    observeNews(it)
                }
                return true
            }
        })
    }

    private fun observeNews(q: String) {
        if (q.isEmpty()) return
        homeViewModel.getNews(q).observe(requireActivity()) { result ->
            when (result) {
                is ApiResult.Error -> {
                    isLoading(false)
                    Toast.makeText(requireActivity(), result.message, Toast.LENGTH_SHORT).show()
                }

                is ApiResult.Loading -> {
                    isLoading(true)
                }

                is ApiResult.Success -> {
                    isLoading(false)
                    setupRecyclerView(result.data)
                }
            }
        }
    }

    private fun setupRecyclerView(news: List<News>) {
        val newsAdapter = NewsAdapter { newsItem ->
            homeViewModel.insertNews(newsItem.toEntity())
            moveToDetail(newsItem)
        }

        val gridLayoutManager = GridLayoutManager(requireActivity(), 2)

        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (news[position].isHeader) 2 else 1
            }
        }

        binding?.rvNews?.apply {
            layoutManager = gridLayoutManager
            setHasFixedSize(true)
            adapter = newsAdapter
        }
        newsAdapter.submitList(news)
    }

    private fun moveToDetail(news: News) {
        val data = HomeFragmentDirections.actionNavigationHomeToDetailActivity(news)
        findNavController().navigate(data)
    }

    private fun isLoading(isLoading: Boolean) {
        binding?.apply {
            shimmer.isVisible = isLoading
            rvNews.isVisible = !isLoading
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}