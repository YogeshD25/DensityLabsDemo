package com.test.densitylabstest.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.densitylabstest.R
import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.databinding.ItemVenueViewholderBinding

class VenueAdapter(private val saveClickInterface: SaveClickInterface) : RecyclerView.Adapter<VenueAdapter.ViewHolder>() {

    private var venueList = emptyList<Venues>()
//    lateinit var itemBinding: ItemVenueViewholderBinding


//    class MyViewHolder(itemBinding: ItemVenueViewholderBinding, saveClickInterface: SaveClickInterface) :
//        RecyclerView.ViewHolder(itemBinding.root)

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val name: TextView = itemView.findViewById(R.id.tv_item_name)
        val distane: TextView = itemView.findViewById(R.id.tv_distance)
        val address: TextView = itemView.findViewById(R.id.tv_address)
        val phone: TextView = itemView.findViewById(R.id.tv_contact)
    }

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
////        itemBinding =
////            ItemVenueViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
////        return MyViewHolder(itemBinding, saveClickInterface)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_venue_viewholder, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentItem = venueList[position]

        holder.itemView.findViewById<TextView>(R.id.tv_item_name).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.tv_distance).text = currentItem.location?.distance.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_address).text = currentItem.location?.address
        holder.itemView.findViewById<TextView>(R.id.tv_contact).text = currentItem.contact?.phone
        if(currentItem.isSaved)
        {
            holder.itemView.findViewById<CheckBox>(R.id.save_icon).isChecked = true
        } else
        {
            holder.itemView.findViewById<CheckBox>(R.id.save_icon).isChecked = false
        }

//        holder.itemView.findViewById<CheckBox>(R.id.save_icon).setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                currentItem.isSaved = true
//                saveClickInterface.onSave(position, currentItem.id, true)
//            } else {
//                currentItem.isSaved = false
//                saveClickInterface.onSave(position, currentItem.id, false)
//
//            }
//        }




        holder.itemView.findViewById<CheckBox>(R.id.save_icon).setOnClickListener{
            val checkBox = it as CheckBox
            if(checkBox.isChecked){
                currentItem.isSaved = true
                saveClickInterface.onSave(position, currentItem.id, true)
            }else{
                currentItem.isSaved = false
                saveClickInterface.onSave(position, currentItem.id, false)
            }
        }

//        if(currentItem.isSaved)
//            itemBinding.saveIcon.isChecked = true
//
//        itemBinding.tvItemName.text = currentItem.name
//        itemBinding.tvDistance.text = currentItem.location?.distance.toString()
//        itemBinding.tvAddress.text = currentItem.location?.address
//        itemBinding.tvContact.text = currentItem.contact?.phone
//
//        itemBinding.saveIcon.setOnCheckedChangeListener { _, isChecked ->
//            if (isChecked) {
//                currentItem.isSaved = true
//                saveClickInterface.onSave(position, currentItem.id, true)
//            } else {
//                currentItem.isSaved = false
//                saveClickInterface.onSave(position, currentItem.id, false)
//
//            }
//        }

    }

    override fun getItemCount(): Int {
        return venueList.size
    }

    fun setData(venueList: List<Venues>) {
        this.venueList = venueList
        notifyDataSetChanged()
    }

    interface SaveClickInterface {
        fun onSave(position: Int, placeId: String, isChecked: Boolean)
    }

}