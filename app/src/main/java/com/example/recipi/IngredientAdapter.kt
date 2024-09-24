package com.example.recipi

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipi.databinding.IngredientBinding

class IngredientAdapter(
    private val context: Context,
    private val ingredientList: List<Ingredient>
) : RecyclerView.Adapter<IngredientAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = IngredientBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ingredient = ingredientList[position]
        holder.binding.tittle.text = ingredient.name
        holder.binding.quantity.text = ingredient.quantity
        val imageResId = getImageResource(ingredient.imgName)
        if (imageResId != 0) {
            holder.binding.img.setImageResource(imageResId)
        } else {
            // Optional: Set a placeholder or error image
            holder.binding.img.setImageResource(R.drawable.fish) // Ensure you have a placeholder image
        }
    }

    override fun getItemCount(): Int = ingredientList.size

    private fun getImageResource(imageName: String): Int {
        val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
        if (resId == 0) {
            // Log or handle the case where the resource was not found
            Log.e("IngredientAdapter", "Resource not found for: $imageName")
        }
        return resId
    }

    inner class ViewHolder(val binding: IngredientBinding) : RecyclerView.ViewHolder(binding.root)
}
