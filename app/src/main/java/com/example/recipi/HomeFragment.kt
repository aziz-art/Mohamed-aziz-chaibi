package com.example.recipi

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipi.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var filteredList: ArrayList<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        setUpSearchBar()
        setupCategoryClickListeners()
        setupCreateClickListener()
    }

    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvAdapter = PopularAdapter(dataList, requireContext()) { recipe ->
            val intent = Intent(requireContext(), DetailActivity::class.java)
            intent.putExtra("RECIPE", recipe)
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
        binding.salad.setOnClickListener { openCategoryFragment("Salad") }
        binding.MainDish.setOnClickListener { openCategoryFragment("Main Dish") }
        binding.Drinks.setOnClickListener { openCategoryFragment("Drink") }
        binding.Desserts.setOnClickListener { openCategoryFragment("Dessert") }
        binding.soups.setOnClickListener { openCategoryFragment("Soup") }
        binding.chips.setOnClickListener { openCategoryFragment("Snacks") }
    }

    private fun openCategoryFragment(category: String) {
        val intent = Intent(requireContext(), CategoryActivity::class.java)
        intent.putExtra("CATEGORY_NAME", category)
        startActivity(intent)
    }

    private fun setupCreateClickListener() {
        // Set up click listener for the create ConstraintLayout
        binding.create.setOnClickListener {
            val intent = Intent(requireContext(), AddRecipe::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
