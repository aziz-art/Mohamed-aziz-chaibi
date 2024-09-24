package com.example.recipi

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.telephony.SmsManager
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class Sign : AppCompatActivity() {

    private lateinit var usernameInput: TextInputEditText
    private lateinit var emailInput: TextInputEditText
    private lateinit var passwordInput: TextInputEditText
    private lateinit var confirmPasswordInput: TextInputEditText
    private lateinit var phoneInput: TextInputEditText
    private lateinit var hideImageView: ImageView
    private lateinit var checkedImageView: ImageView
    private lateinit var signUpButton: Button

    companion object {
        private const val REQUEST_SMS_PERMISSION = 123
        private const val TAG = "SignActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign)

        // Initialize views
        usernameInput = findViewById(R.id.username)
        emailInput = findViewById(R.id.emailEditText)
        passwordInput = findViewById(R.id.pswd)
        confirmPasswordInput = findViewById(R.id.cpswd)
        phoneInput = findViewById(R.id.phone)
        hideImageView = findViewById(R.id.hide)
        checkedImageView = findViewById(R.id.checked)
        signUpButton = findViewById(R.id.signup)

        // Listener for the "login" text
        findViewById<TextView>(R.id.login).setOnClickListener {
            // Navigate to the login activity
            val intent = Intent(this, LoginAct::class.java)
            startActivity(intent)
        }

        // Toggle password visibility
        hideImageView.setOnClickListener {
            togglePasswordVisibility()
        }

        // Check if passwords match
        confirmPasswordInput.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                checkPasswordMatch()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        // Sign up button action
        signUpButton.setOnClickListener {
            validatePhoneNumber()
        }
    }

    private fun togglePasswordVisibility() {
        val isPasswordVisible = passwordInput.inputType and InputType.TYPE_TEXT_VARIATION_PASSWORD == 0
        val newInputType = if (isPasswordVisible) {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        } else {
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        }

        passwordInput.inputType = newInputType
        confirmPasswordInput.inputType = newInputType

        passwordInput.setSelection(passwordInput.text?.length ?: 0)
        confirmPasswordInput.setSelection(confirmPasswordInput.text?.length ?: 0)

        hideImageView.setImageResource(if (isPasswordVisible) R.drawable.show else R.drawable.hide)
    }

    private fun checkPasswordMatch() {
        val password = passwordInput.text.toString()
        val confirmPassword = confirmPasswordInput.text.toString()
        if (password == confirmPassword) {
            checkedImageView.visibility = ImageView.VISIBLE
            confirmPasswordInput.setBackgroundResource(android.R.color.transparent)
        } else {
            checkedImageView.visibility = ImageView.INVISIBLE
            confirmPasswordInput.setBackgroundResource(R.drawable.error)
        }
    }

    private fun validatePhoneNumber() {
        val phoneNumber = phoneInput.text.toString()
        val regex = "^(90|91|92|93|94|95|96|97|98|99|40|41|42|43|44|45|46|47|48|49|50|51|52|53|54|55|56|57|58|59|20|21|22|23|24|25|26|27|28|29)\\d{6}$".toRegex()

        if (phoneNumber.matches(regex)) {
            checkPermissionsAndSendSMS()
        } else {
            phoneInput.setBackgroundResource(R.drawable.error)
            Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkPermissionsAndSendSMS() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            sendOTP(phoneInput.text.toString())
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), REQUEST_SMS_PERMISSION)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_SMS_PERMISSION && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            sendOTP(phoneInput.text.toString())
        } else {
            Toast.makeText(this, "SMS permission required", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sendOTP(phoneNumber: String) {
        val otpCode = generateOTP()
        val smsManager = SmsManager.getDefault()
        val message = "Your confirmation code is $otpCode"
        try {
            smsManager.sendTextMessage(phoneNumber, null, message, null, null)
            val intent = Intent(this, Otp::class.java)
            intent.putExtra("email", emailInput.text.toString())
            intent.putExtra("phone", phoneNumber)
            intent.putExtra("otpCode", otpCode)
            startActivity(intent)
        } catch (e: Exception) {
            Log.e(TAG, "Failed to send OTP: ${e.message}", e)
            Toast.makeText(this, "Failed to send OTP", Toast.LENGTH_SHORT).show()
        }
    }

    private fun generateOTP(): String {
        return (1000 + Random().nextInt(9000)).toString()
    }

    private fun performSignup() {
        val username = usernameInput.text.toString()
        val email = emailInput.text.toString()
        val password = passwordInput.text.toString()
        val request = SignupRequest(username, email, password)

        // Launch a coroutine to perform the network request
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.api.signup(request)
                }

                if (response.isSuccessful) {
                    val authResponse = response.body()
                    Toast.makeText(this@Sign, "Signup successful: ${authResponse?.message}", Toast.LENGTH_LONG).show()
                    // Navigate to another activity or perform other actions
                } else {
                    Toast.makeText(this@Sign, "Signup failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Signup error: ${e.message}", e)
                Toast.makeText(this@Sign, "Error: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}
