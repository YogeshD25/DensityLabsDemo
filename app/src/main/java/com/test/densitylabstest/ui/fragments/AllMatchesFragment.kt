package com.test.densitylabstest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.densitylabstest.R
import com.test.densitylabstest.databinding.FragmentAllMatchesBinding
import com.test.densitylabstest.ui.adapter.VenueAdapter
import com.test.densitylabstest.ui.viewmodel.VenueViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class AllMatchesFragment : Fragment() {

    private val viewModel: VenueViewModel by viewModels()
    private lateinit var venueAdapter: VenueAdapter
    private lateinit var binding: FragmentAllMatchesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        setupAdapter()
        binding = FragmentAllMatchesBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun setupAdapter() {
        val recyclerViewTopFeed = binding.recyclerview
        recyclerViewTopFeed.adapter = venueAdapter
        recyclerViewTopFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }
}