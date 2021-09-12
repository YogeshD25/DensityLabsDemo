package com.test.densitylabstest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.densitylabstest.R
import com.test.densitylabstest.databinding.FragmentAllMatchesBinding
import com.test.densitylabstest.ui.adapter.VenueAdapter
import com.test.densitylabstest.ui.viewmodel.VenueViewModel
import com.test.densitylabstest.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job


@AndroidEntryPoint
class AllMatchesFragment : Fragment(),VenueAdapter.SaveClickInterface {

    private val viewModel: VenueViewModel by viewModels()
    private lateinit var venueAdapter: VenueAdapter
    private lateinit var binding: FragmentAllMatchesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAllMatchesBinding.inflate(inflater, container, false)
        venueAdapter = VenueAdapter(this)
        setupAdapter()
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {

        viewModel.fetchAllVenueData()
        binding.progressBar.visibility = View.VISIBLE
        viewModel.allVenuesData.observe(viewLifecycleOwner) { response ->
            when (response) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    response.data?.let {
                        venueAdapter.setData(it)
                    }

                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    response.error.toString().let {
                        Snackbar.make(binding.root, it, 5000).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }

    }

    private fun setupAdapter() {
        val recyclerViewTopFeed = binding.recyclerview
        recyclerViewTopFeed.adapter = venueAdapter
        recyclerViewTopFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onSave(position: Int, id: String, isChecked: Boolean) {
        viewModel.setUserSavedMatch(id, isChecked)
    }
}