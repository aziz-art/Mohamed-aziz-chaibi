package com.example.recipi


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.recipi.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the recipe data from the intent
        val recipe = intent.getParcelableExtra<Recipe>("recipe")

        if (recipe != null) {
            // Set the data to views
            binding.tvTitle.text = recipe.tittle
            binding.tvSubTitle.text = recipe.category
            binding.imgThumb.setImageResource(resources.getIdentifier(recipe.img, "drawable", packageName))

            // Format the ingredients list
            val ingredientsText = recipe.ing.joinToString(separator = "\n") { "${it.name}: ${it.quantity}" }
            binding.tvIngredients.text = ingredientsText

            binding.tvInstructions.text = recipe.des
        }

        binding.toolbarDetail.setNavigationOnClickListener {
            finish()
        }
    }
}
