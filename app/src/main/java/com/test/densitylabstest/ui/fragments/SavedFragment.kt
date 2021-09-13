package com.test.densitylabstest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.test.densitylabstest.databinding.FragmentSavedBinding
import com.test.densitylabstest.ui.adapter.VenueSavedAdapter
import com.test.densitylabstest.ui.viewmodel.VenueViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SavedFragment : Fragment(), VenueSavedAdapter.SaveClickInterface {

    private val viewModel: VenueViewModel by viewModels()
    private lateinit var venueSavedAdapter: VenueSavedAdapter
    private lateinit var binding: FragmentSavedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSavedBinding.inflate(inflater, container, false)
        venueSavedAdapter = VenueSavedAdapter(this)
        viewModel.getSavedMarkData()
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        val recyclerViewTopFeed = binding.recyclerview
        recyclerViewTopFeed.adapter = venueSavedAdapter
        recyclerViewTopFeed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        viewModel.allSavedVenuesData.observe(viewLifecycleOwner,{
            viewModel.getSavedMarkData()
            venueSavedAdapter.setData(it)
        })
    }

    override fun onSave(position: Int, id: String, isChecked: Boolean) {
        viewModel.setUserSavedMatch(id, isChecked)
    }

}