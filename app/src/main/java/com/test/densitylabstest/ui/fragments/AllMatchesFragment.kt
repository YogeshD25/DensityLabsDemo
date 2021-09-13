package com.test.densitylabstest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.test.densitylabstest.databinding.FragmentAllMatchesBinding
import com.test.densitylabstest.ui.adapter.VenueAdapter
import com.test.densitylabstest.ui.viewmodel.VenueViewModel
import com.test.densitylabstest.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class AllMatchesFragment : Fragment(), VenueAdapter.SaveClickInterface {

    private val viewModel: VenueViewModel by viewModels()
    private lateinit var venueAdapter: VenueAdapter
    private lateinit var binding: FragmentAllMatchesBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentAllMatchesBinding.inflate(inflater, container, false)
        venueAdapter = VenueAdapter(this)
        viewModel.fetchAllVenueData()
        setupAdapter()
        setupObserver()
        return binding.root
    }

    private fun setupObserver() {


        lifecycleScope.launchWhenCreated {
            delay(2000)
        }
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
        binding.recyclerview.apply {
            this.adapter = venueAdapter
            this.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            this.setHasFixedSize(true)
        }

    }

    override fun onSave(position: Int, id: String, isChecked: Boolean) {
        viewModel.setUserSavedMatch(id, isChecked)
    }
}