package com.example.recipi

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton

class Otp : AppCompatActivity() {

    private lateinit var emailTextView: TextView
    private lateinit var phoneTextView: TextView
    private lateinit var otp1: EditText
    private lateinit var otp2: EditText
    private lateinit var otp3: EditText
    private lateinit var otp4: EditText
    private lateinit var verifyButton: Button
    private lateinit var resendButton: TextView
    private var otpCode: String? = null
    private var email: String? = null
    private var phone: String? = null
    private var countDownTimer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        val verifButton: Button = findViewById(R.id.verif)

        verifButton.setOnClickListener {
            // Create an intent to navigate to the HomeActivity
            val intent = Intent(this, HomeActivity::class.java)


        }
        emailTextView = findViewById(R.id.email)
        phoneTextView = findViewById(R.id.phone)
        otp1 = findViewById(R.id.otp1)
        otp2 = findViewById(R.id.otp2)
        otp3 = findViewById(R.id.otp3)
        otp4 = findViewById(R.id.otp4)
        verifyButton = findViewById(R.id.verif)
        resendButton = findViewById(R.id.resendBtn)

        email = intent.getStringExtra("email")
        phone = intent.getStringExtra("phone")
        otpCode = intent.getStringExtra("otpCode")

        emailTextView.text = email
        phoneTextView.text = phone

        startResendTimer()

        verifyButton.setOnClickListener {
            verifyOTP()
        }

        resendButton.setOnClickListener {
            // Resend OTP logic
            resendOTP()
        }
    }

    private fun verifyOTP() {
        val enteredOTP = otp1.text.toString() + otp2.text.toString() + otp3.text.toString() + otp4.text.toString()

        if (enteredOTP == otpCode) {
            Toast.makeText(this, "OTP Verified Successfully", Toast.LENGTH_SHORT).show()
            // Proceed to the next activity
        } else {
            Toast.makeText(this, "Invalid OTP", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startResendTimer() {
        resendButton.isEnabled = false
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val seconds = (millisUntilFinished / 1000).toInt()
                resendButton.text = "Resend OTP in ${String.format("%02d:%02d", seconds / 60, seconds % 60)}"
            }

            override fun onFinish() {
                resendButton.isEnabled = true
                resendButton.text = "Resend OTP"
            }
        }
        countDownTimer?.start()
    }

    private fun resendOTP() {
        // Generate a new OTP and resend it
        val newOtpCode = generateOTP()
        otpCode = newOtpCode // Update the OTP code
        // Send the new OTP code via SMS (you may want to handle permission here as well)
        // For demonstration purposes, let's use a placeholder for sending SMS
        // You should call the method to send the OTP code here
    }

    private fun generateOTP(): String {
        return (1000 + (0..8999).random()).toString()
    }
}
