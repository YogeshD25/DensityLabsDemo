package com.test.densitylabstest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.databinding.ItemVenueViewholderBinding

class VenueAdapter(saveClickInterface: SaveClickInterface) : RecyclerView.Adapter<VenueAdapter.MyViewHolder>() {

    private var categoryList = emptyList<Venues>()
    private lateinit var context: Context
    lateinit var itemBinding: ItemVenueViewholderBinding

    private val saveClickInterface: SaveClickInterface = saveClickInterface


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

        if(currentItem.isSaved)
        itemBinding.saveIcon.isChecked = true

        itemBinding.tvItemName.text = currentItem.name
        itemBinding.tvDistance.text = currentItem.location?.distance.toString()
        itemBinding.tvAddress.text = currentItem.location?.address
        itemBinding.tvContact.text = currentItem.contact?.phone

        itemBinding.saveIcon.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentItem.isSaved = true
                saveClickInterface.onSave(position, currentItem.id, true)
            } else {
                currentItem.isSaved = false
                saveClickInterface.onSave(position, currentItem.id, false)

            }
        }

    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setData(categories: List<Venues>) {
        this.categoryList = categories
        notifyDataSetChanged()
    }

    interface SaveClickInterface {
        fun onSave(position: Int, placeId: String, isChecked: Boolean)
    }

}