package com.example.recipi


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
// RecipeDao.kt
@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipe WHERE category = :category")
    fun getRecipesByCategory(category: String): List<Recipe>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(recipes: List<Recipe>)
}
