package com.example.recipi


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recipi.databinding.CategoryRvBinding

class CategoryAdapter(private val recipeList: List<Recipe>) : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CategoryRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.binding.tittle.text = recipe.tittle
        holder.binding.duration.text = recipe.duration

        // Load image using Glide
        val context = holder.binding.img.context
        val resourceId = context.resources.getIdentifier(recipe.img, "drawable", context.packageName)
        Glide.with(context).load(resourceId).into(holder.binding.img)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class ViewHolder(val binding: CategoryRvBinding) : RecyclerView.ViewHolder(binding.root)
}
