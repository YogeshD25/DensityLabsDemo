package com.test.densitylabstest.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.test.densitylabstest.R
import com.test.densitylabstest.data.local.entities.Venues
import com.test.densitylabstest.databinding.ItemVenueViewholderBinding

class VenueSavedAdapter(saveClickInterface: SaveClickInterface) :
    RecyclerView.Adapter<VenueSavedAdapter.MyViewHolder>() {

    private var categoryList = emptyList<Venues>()
    lateinit var itemBinding: ItemVenueViewholderBinding

    private val saveClickInterface: SaveClickInterface = saveClickInterface


    class MyViewHolder(itemBinding: ItemVenueViewholderBinding,saveClickInterface: SaveClickInterface) :
        RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        itemBinding =
            ItemVenueViewholderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(itemBinding,saveClickInterface)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = categoryList[position]


        if(currentItem.isSaved)
        holder.itemView.findViewById<CheckBox>(R.id.save_icon).isChecked = true


        holder.itemView.findViewById<TextView>(R.id.tv_item_name).text = currentItem.name
        holder.itemView.findViewById<TextView>(R.id.tv_distance).text = currentItem.location?.distance.toString()
        holder.itemView.findViewById<TextView>(R.id.tv_address).text = currentItem.location?.address
        holder.itemView.findViewById<TextView>(R.id.tv_contact).text = currentItem.contact?.phone

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
//        itemBinding.saveIcon.isChecked = true
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