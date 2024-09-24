package com.example.recipi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LoginAct : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login2)

        // Find views by ID
        val emailEditText: TextInputEditText = findViewById(R.id.emailEditText)
        val passwordEditText: TextInputEditText = findViewById(R.id.pswd)
        val signInButton: Button = findViewById(R.id.signInButton) // Update ID to match your button

        signInButton.setOnClickListener {
            // Get text from email and password fields
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            // Check if both fields are equal to "test"
            if (email == "test" && password == "test") {
                // Create an intent to navigate to HomeActivity
                val intent = Intent(this, HomeActivity::class.java)
                // Start the new activity
                startActivity(intent)
            } else {
                // Optionally, show an error message or perform other actions
            }
        }

        // Set up the sign-up click listener
        val signUpTextView: TextView = findViewById(R.id.login)
        signUpTextView.setOnClickListener {
            // Create an intent to navigate to the Sign activity
            val intent = Intent(this, Sign::class.java)
            // Start the new activity
            startActivity(intent)
        }
    }
}
