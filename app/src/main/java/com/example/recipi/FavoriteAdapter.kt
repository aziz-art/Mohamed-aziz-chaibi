package com.example.recipi

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipi.databinding.FavouriteRvBinding

class FavouriteAdapter(
    private var dataList: MutableList<Recipe>, // Changed to MutableList
    private val context: Context,
    private val onItemRemoved: (Int) -> Unit // Callback to handle item removal
) : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FavouriteRvBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = dataList[position]

        holder.binding.popularImg.setImageResource(getImageResource(recipe.img))
        holder.binding.popularTxt.text = recipe.tittle
        holder.binding.popularTime.text = recipe.duration

        // Set a click listener for the like button
        holder.binding.imageView23.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun getImageResource(imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    private fun removeItem(position: Int) {
        // Remove item from the list
        dataList.removeAt(position)
        notifyItemRemoved(position)

        // Notify the activity/fragment that an item has been removed
        onItemRemoved(position)
    }

    inner class ViewHolder(val binding: FavouriteRvBinding) :
        RecyclerView.ViewHolder(binding.root)
}
