package com.djr.newssphere.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.djr.newssphere.data.local.AppDatabase
import com.djr.newssphere.data.local.HeadlineDao
import com.djr.newssphere.data.model.Headline
import com.djr.newssphere.data.remote.NewsRepository
import com.djr.newssphere.data.remote.RetrofitClient
import com.djr.newssphere.databinding.FragmentHeadlinesBinding
import com.djr.newssphere.ui.adapters.HeadlineAdapter
import com.djr.newssphere.ui.viewmodels.HeadlinesViewModel
import com.djr.newssphere.ui.viewmodels.HeadlinesViewModelFactory


class HeadlinesFragment : Fragment() {
    lateinit var binding: FragmentHeadlinesBinding
    private val viewModel: HeadlinesViewModel by viewModels {
        HeadlinesViewModelFactory(
            NewsRepository(RetrofitClient.create(), getHeadlineDao()),
            getHeadlineDao()
        )
    }
    private lateinit var headlinesAdapter: HeadlineAdapter

    private fun getHeadlineDao(): HeadlineDao {
        val context = requireContext()
        val database = AppDatabase.getDatabase(context)
        return database.headlineDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHeadlinesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        headlinesAdapter = HeadlineAdapter { headline ->
            onHeadlineClicked(headline)
        }

        binding.headlinesRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = headlinesAdapter
        }

        viewModel.headlines.observe(viewLifecycleOwner) { headlines ->
            headlinesAdapter.submitList(headlines)
        }

        viewModel.fetchTopHeadlines()
    }

    private fun onHeadlineClicked(headline: Headline) {
        val action =
            HeadlinesFragmentDirections.actionHeadlinesFragmentToHeadlineDetailFragment(headline)
        findNavController().navigate(action)
    }
}