// RecipeAdapter.kt
package com.example.recipi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.recipi.databinding.MyRvBinding

class RecipeAdapter(private val recipeList: List<RecipeData>) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val binding = MyRvBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = recipeList[position]
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = recipeList.size

    class RecipeViewHolder(private val binding: MyRvBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: RecipeData) {
            binding.imgThumb.setImageResource(recipe.imgResId)
            binding.popularTxt.text = recipe.name
            binding.popularTime.text = recipe.duration
        }
    }
}
