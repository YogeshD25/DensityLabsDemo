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
import com.test.densitylabstest.databinding.FragmentSavedBinding
import com.test.densitylabstest.ui.adapter.VenueAdapter
import com.test.densitylabstest.ui.viewmodel.VenueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(), VenueAdapter.SaveClickInterface {

    private val viewModel: VenueViewModel by viewModels()
    private lateinit var venueAdapter: VenueAdapter
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        venueAdapter = VenueAdapter(this)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        val recyclerViewTopFeed = binding.recyclerview
        recyclerViewTopFeed.adapter = venueAdapter
        recyclerViewTopFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.allSavedVenuesData.observe(viewLifecycleOwner,{
            viewModel.getSavedMarkData()
            venueAdapter.setData(it)
        })
    }

    override fun onSave(position: Int, id: String, isChecked: Boolean) {
        viewModel.setUserSavedMatch(id, isChecked)
    }

}