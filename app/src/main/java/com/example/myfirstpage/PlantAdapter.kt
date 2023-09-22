package com.example.myfirstpage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstpage.databinding.PlantItemBinding

class PlantAdapter(private val listener: Listener) :
    RecyclerView.Adapter<PlantAdapter.PlantHolder>() {

    var plantList = ArrayList<PlantUI>()

    class PlantHolder(item: View) : RecyclerView.ViewHolder(item) {
        val binding = PlantItemBinding.bind(item)
        fun bind(plant: PlantUI, listener: Listener) = with(binding) {
            tvTitle.text = plant.title
            tvId.text = plant.id.toString()
            itemView.setOnClickListener {
                listener.onClick(plant)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlantHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.plant_item, parent, false)
        return PlantHolder(view)
    }

    override fun getItemCount(): Int {
        return plantList.size
    }

    override fun onBindViewHolder(holder: PlantHolder, position: Int) {
        holder.bind(plantList[position], listener)
    }


    fun setPlant(plants: List<PlantUI>) {
        val result = DiffUtil.calculateDiff(PlantDiffCallback(plantList, plants))
        plantList.clear()
        plantList.addAll(plants)
        result.dispatchUpdatesTo(this)

    }

    fun interface Listener {
        fun onClick(plant: PlantUI)
    }
}


class PlantDiffCallback(
    private val oldPlants: List<PlantUI>,
    private val newPlants: List<PlantUI>
) : DiffUtil.Callback() {

    override fun getOldListSize() = oldPlants.size

    override fun getNewListSize() = newPlants.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldPlants[oldItemPosition].id == newPlants[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldPlants[oldItemPosition] == newPlants[newItemPosition]
}
