package com.rahmad.newsworld.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahmad.newsworld.data.source.local.entity.NewsEntity
import com.rahmad.newsworld.databinding.FragmentHistoryBinding
import com.rahmad.newsworld.ui.adapter.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HistoryFragment : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding
    private val historyViewModel by viewModels<HistoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        historyViewModel.getHasBeenReadNews().observe(viewLifecycleOwner) {
            setupRecyclerView(it)
            isNewsEmpty(it.isEmpty())
        }
    }

    private fun setupRecyclerView(news: List<NewsEntity>) {
        val historyAdapter = HistoryAdapter()
        binding?.rvHistory?.apply {
            layoutManager = LinearLayoutManager(requireActivity())
            adapter = historyAdapter
            setHasFixedSize(true)
        }
        historyAdapter.submitList(news)
    }

    private fun isNewsEmpty(isEmpty: Boolean) {
        binding?.apply {
            tvEmpty.isVisible = isEmpty
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}