package com.example.recipi

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipi.databinding.FragmentLikesBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LikesFragment : Fragment() {

    private var _binding: FragmentLikesBinding? = null
    private val binding get() = _binding!!
    private lateinit var rvAdapter: FavouriteAdapter
    private lateinit var favouriteList: MutableList<Recipe>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLikesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val backImageView: ImageView = view.findViewById(R.id.back)

        // Set an OnClickListener on the ImageView
        backImageView.setOnClickListener {
            val intent = Intent(requireContext(), Top::class.java).apply {
                putExtra("SHOW_FRAGMENT", "HOME")
            }
            startActivity(intent)
        }

        // Initialize RecyclerView and load saved favourites
        setUpRecyclerView()
        loadFromInternalStorage()

        // Retrieve the favorite recipe from the arguments
        val favoriteRecipe = arguments?.getParcelable<Recipe>("FAVORITE_RECIPE")
        if (favoriteRecipe != null) {
            addRecipeToFavourites(favoriteRecipe)
        }
    }

    private fun setUpRecyclerView() {
        favouriteList = ArrayList()
        binding.favourite.layoutManager = LinearLayoutManager(requireContext())

        rvAdapter = FavouriteAdapter(favouriteList, requireContext()) { position ->
            // This callback is invoked when an item is removed
            saveToInternalStorage()
        }

        binding.favourite.adapter = rvAdapter
    }

    private fun addRecipeToFavourites(recipe: Recipe) {
        if (!favouriteList.contains(recipe)) {
            favouriteList.add(recipe)
            rvAdapter.notifyDataSetChanged()
            saveToInternalStorage()
        }
    }

    private fun saveToInternalStorage() {
        val sharedPreferences = requireContext().getSharedPreferences("FavouritesPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Convert the list of recipes to a JSON string
        val gson = Gson()
        val json = gson.toJson(favouriteList)

        editor.putString("favourite_recipes", json)
        editor.apply()
    }

    private fun loadFromInternalStorage() {
        val sharedPreferences = requireContext().getSharedPreferences("FavouritesPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("favourite_recipes", null)

        val type = object : TypeToken<MutableList<Recipe>>() {}.type
        favouriteList = gson.fromJson(json, type) ?: ArrayList()

        rvAdapter = FavouriteAdapter(favouriteList, requireContext()) { position ->
            // This callback is invoked when an item is removed
            saveToInternalStorage()
        }

        binding.favourite.adapter = rvAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
