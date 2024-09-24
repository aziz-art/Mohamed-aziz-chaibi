package com.example.recipi

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class Top : AppCompatActivity() {
    private var selectedTab = 1

    private lateinit var homeLayout: LinearLayout
    private lateinit var likeLayout: LinearLayout
    private lateinit var notifLayout: LinearLayout
    private lateinit var profileLayout: LinearLayout
    private lateinit var homeImage: ImageView
    private lateinit var likeImage: ImageView
    private lateinit var notifImage: ImageView
    private lateinit var profileImage: ImageView
    private lateinit var homeTxt: TextView
    private lateinit var likeTxt: TextView
    private lateinit var notifTxt: TextView
    private lateinit var profileTxt: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top)

        // Initialize views
        homeLayout = findViewById(R.id.homeLayout)
        likeLayout = findViewById(R.id.likeLayout)
        notifLayout = findViewById(R.id.notifLayout)
        profileLayout = findViewById(R.id.profileLayout)
        homeImage = findViewById(R.id.homeimg)
        likeImage = findViewById(R.id.likeimg)
        notifImage = findViewById(R.id.notifimg)
        profileImage = findViewById(R.id.profileimg)
        homeTxt = findViewById(R.id.hometxt)
        likeTxt = findViewById(R.id.liketxt)
        notifTxt = findViewById(R.id.notiftxt)
        profileTxt = findViewById(R.id.profiletxt)

        // Handle intent to show specific fragment
        val showFragment = intent.getStringExtra("SHOW_FRAGMENT")
        if (showFragment == "LIKES") {
            showLikesFragment(intent.getParcelableExtra("FAVORITE_RECIPE"))
        } else {
            // Default to HomeFragment if no specific fragment is requested
            showHomeFragment()
        }

        homeLayout.setOnClickListener { switchTab(1) }
        likeLayout.setOnClickListener { switchTab(2) }
        notifLayout.setOnClickListener { switchTab(3) }
        profileLayout.setOnClickListener { switchTab(4) }
    }

    private fun showHomeFragment() {
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, HomeFragment())
            .commit()
        updateUIForTab(homeLayout, homeTxt, homeImage, R.drawable.homed, R.drawable.round_home)
    }

    private fun showLikesFragment(recipe: Recipe?) {
        val fragment = LikesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("FAVORITE_RECIPE", recipe)
            }
        }
        supportFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .replace(R.id.fragmentContainer, fragment)
            .commit()
        updateUIForTab(likeLayout, likeTxt, likeImage, R.drawable.liked, R.drawable.round_like)
    }

    private fun switchTab(tabIndex: Int) {
        if (selectedTab != tabIndex) {
            when (tabIndex) {
                1 -> showHomeFragment()
                2 -> {
                    supportFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, LikesFragment())
                        .commit()
                    updateUIForTab(likeLayout, likeTxt, likeImage, R.drawable.liked, R.drawable.round_like)
                }
                3 -> {
                    supportFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, NotifFragment())
                        .commit()
                    updateUIForTab(notifLayout, notifTxt, notifImage, R.drawable.notified, R.drawable.round_notif)
                }
                4 -> {
                    supportFragmentManager.beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentContainer, ProfileFragment())
                        .commit()
                    updateUIForTab(profileLayout, profileTxt, profileImage, R.drawable.profiled, R.drawable.round_profile)
                }
            }
            selectedTab = tabIndex
        }
    }

    private fun updateUIForTab(layout: LinearLayout, textView: TextView, imageView: ImageView, imageResId: Int, backgroundResId: Int) {
        homeTxt.visibility = View.GONE
        likeTxt.visibility = View.GONE
        notifTxt.visibility = View.GONE
        profileTxt.visibility = View.GONE

        homeImage.setImageResource(R.drawable.home)
        likeImage.setImageResource(R.drawable.like)
        notifImage.setImageResource(R.drawable.notif)
        profileImage.setImageResource(R.drawable.profile)

        homeLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        likeLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        notifLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))
        profileLayout.setBackgroundColor(ContextCompat.getColor(this, android.R.color.transparent))

        textView.visibility = View.VISIBLE
        imageView.setImageResource(imageResId)
        layout.setBackgroundResource(backgroundResId)

        val scaleAnimation = ScaleAnimation(
            0.8f, 1.0f, 1f, 1f,
            Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, 0.0f
        ).apply {
            duration = 200
            fillAfter = true
        }
        layout.startAnimation(scaleAnimation)
    }
}
