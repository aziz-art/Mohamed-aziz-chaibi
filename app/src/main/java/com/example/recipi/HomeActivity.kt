package com.example.recipi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipi.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var filteredList: ArrayList<Recipe>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpRecyclerView()
        setUpSearchBar()
        setupCategoryClickListeners()
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvAdapter = PopularAdapter(dataList, this) { recipe ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("RECIPE", recipe)  // Pass the selected recipe to DetailActivity
            startActivity(intent)
        }
        binding.rvPopular.adapter = rvAdapter

        // Populate dataList with recipes
        dataList.addAll(Recipe.generateDummyRecipes())
        rvAdapter.notifyDataSetChanged()
    }

    private fun setUpSearchBar() {
        binding.editTextText3.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filter(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun filter(text: String) {
        filteredList = ArrayList()
        for (recipe in dataList) {
            if (recipe.tittle.contains(text, true) || recipe.des.contains(text, true) || recipe.ing.any { it.name.contains(text, true) || it.quantity.contains(text, true) }) {
                filteredList.add(recipe)
            }
        }
        rvAdapter.updateList(filteredList)
    }

    private fun setupCategoryClickListeners() {
        binding.salad.setOnClickListener { openCategoryActivity("Salad") }
        binding.MainDish.setOnClickListener { openCategoryActivity("Main Dish") }
        binding.Drinks.setOnClickListener { openCategoryActivity("Drink") }
        binding.Desserts.setOnClickListener { openCategoryActivity("Dessert") }
        binding.soups.setOnClickListener { openCategoryActivity("Soup") }
        binding.chips.setOnClickListener { openCategoryActivity("Snacks") }
    }

    private fun openCategoryActivity(category: String) {
        val intent = Intent(this, CategoryActivity::class.java)
        intent.putExtra("CATEGORY_NAME", category)
        startActivity(intent)
    }
}
