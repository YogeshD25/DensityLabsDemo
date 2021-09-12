package com.test.densitylabstest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.databinding.ItemVenueViewholderBinding

class VenueAdapter : RecyclerView.Adapter<VenueAdapter.MyViewHolder>() {

    private var categoryList = emptyList<Venues>()
    private lateinit var context: Context
    lateinit var itemBinding: ItemVenueViewholderBinding

    class MyViewHolder(itemBinding: ItemVenueViewholderBinding) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        context = parent.context
        itemBinding =
            ItemVenueViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = categoryList[position]

        itemBinding.name.text = currentItem.name

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categories: List<Venues>) {
        this.categoryList = categories
        notifyDataSetChanged()
    }
}