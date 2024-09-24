package com.example.recipi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.recipi.databinding.PopularRvItemBinding

class PopularAdapter(
    private var dataList: List<Recipe>,
    private val context: Context,
    private val onClick: (Recipe) -> Unit
) : RecyclerView.Adapter<PopularAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PopularRvItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val recipe = dataList[position]

        holder.binding.popularImg.setImageResource(getImageResource(recipe.img))
        holder.binding.popularTxt.text = recipe.tittle
        holder.binding.popularTime.text = recipe.duration

        holder.itemView.setOnClickListener {
            onClick(recipe)
        }

        holder.binding.like.setOnClickListener {
            val intent = Intent(context, Top::class.java).apply {
                putExtra("SHOW_FRAGMENT", "LIKES")
                putExtra("FAVORITE_RECIPE", recipe)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    private fun getImageResource(imageName: String): Int {
        return context.resources.getIdentifier(imageName, "drawable", context.packageName)
    }

    fun updateList(newList: List<Recipe>) {
        dataList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: PopularRvItemBinding) : RecyclerView.ViewHolder(binding.root)
}
