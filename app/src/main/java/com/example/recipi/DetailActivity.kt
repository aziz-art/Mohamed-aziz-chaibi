package com.example.recipi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipi.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var ingredientAdapter: IngredientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Retrieve data from intent
        val recipe = intent.getParcelableExtra<Recipe>("RECIPE")
        recipe?.let {
            val recipeName = it.tittle
            val recipeCategory = it.category
            val recipeImage = it.img
            val recipeInstructions = it.des
            val ingredientsList = it.ing

            // Set data to views
            binding.name.text = recipeName
            binding.category.text = recipeCategory
            binding.imgThumb.setImageResource(getImageResource(recipeImage))
            binding.instructions.text = recipeInstructions

            // Initialize IngredientAdapter with the list of ingredients
            ingredientAdapter = IngredientAdapter(this, ingredientsList)
            binding.recycle.layoutManager = LinearLayoutManager(this)
            binding.recycle.adapter = ingredientAdapter
        }

        // Handle back button
        binding.imageView17.setOnClickListener {
            finish()
        }
    }

    private fun getImageResource(imageName: String): Int {
        val resId = resources.getIdentifier(imageName, "drawable", packageName)
        if (resId == 0) {
            Log.e("DetailActivity", "Resource not found for: $imageName")
        }
        return resId
    }
}
