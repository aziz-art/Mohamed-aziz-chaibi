package com.example.recipi

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipi.databinding.ActivityCategoryBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoryBinding
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var filteredList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val backImageView: ImageView = findViewById(R.id.back)
        backImageView.setOnClickListener {
            navigateToTopActivityWithHomeFragment()
        }

        val categoryName = intent.getStringExtra("CATEGORY_NAME")
        binding.tittle.text = categoryName

        recipeList = ArrayList(Recipe.generateDummyRecipes())
        filteredList = recipeList.filter { it.category == categoryName } as ArrayList<Recipe>

        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
        rvAdapter = CategoryAdapter(filteredList)
        binding.rvCategory.layoutManager = LinearLayoutManager(this)
        binding.rvCategory.adapter = rvAdapter
    }

    private fun navigateToTopActivityWithHomeFragment() {
        val intent = Intent(this, Top::class.java).apply {
            putExtra("SHOW_FRAGMENT", "HOME")
        }
        startActivity(intent)
        finish() // Optionally finish CategoryActivity to prevent returning to it
    }
}
