package com.example.recipi

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class AddRecipe : AppCompatActivity() {

    private lateinit var durationSpinner: Spinner
    private lateinit var popularImageView: ImageView
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var backImageView: ImageView

    private val REQUEST_CODE_PERMISSION = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_recipe)

        // Initialize UI elements
        durationSpinner = findViewById(R.id.duration_spinner)
        popularImageView = findViewById(R.id.popular_img)
        constraintLayout = findViewById(R.id.constraintLayout7)
        backImageView = findViewById(R.id.back) // Ensure the correct ID is used

        // Set up Spinner
        val durations = arrayOf("Duration", "15 min", "30 min", "45 min", "1h", "2h", "2h 15 min", "2h 30 min", "2h 45 min")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, durations)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        durationSpinner.adapter = adapter

        // Request permission if not granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), REQUEST_CODE_PERMISSION)
        }

        // Set click listener to open image picker
        constraintLayout.setOnClickListener {
            openImagePicker()
        }

        // Set click listener for back button
        backImageView.setOnClickListener {
            navigateToHomeFragment()
        }
    }

    // Method to open image picker
    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = "image/*"
        }
        try {
            imagePickerLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(this, "Error opening image picker: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Handle the selected image result
    private val imagePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK && result.data != null) {
            val selectedImageUri: Uri? = result.data?.data
            if (selectedImageUri != null) {
                popularImageView.setImageURI(selectedImageUri)
                Toast.makeText(this, "Image loaded successfully", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to load image", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "Image selection canceled", Toast.LENGTH_SHORT).show()
        }
    }

    // Method to navigate to HomeFragment
    private fun navigateToHomeFragment() {
        val intent = Intent(this, Top::class.java).apply {
            putExtra("SHOW_FRAGMENT", "HOME")
        }
        startActivity(intent)
        finish() // Optional: close this activity to prevent it from being added to the back stack
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show()
            } else {
                // Permission denied
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
