package com.example.movieapp.activity

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        startUp()
    }


    private fun startUp() {
        navigationSetup()
        bottomNavigationAnimation()
    }

    private fun navigationSetup() {

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        findViewById<BottomNavigationView>(R.id.bottom_nav)
            .setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.mainFragment -> showBottomNav()
                R.id.listFragment -> showBottomNav()
                R.id.accountFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun bottomNavigationAnimation() {
        animator(100f)
    }

    private fun showBottomNav() {
        lifecycleScope.launch {
            delay(100)
            animator(0f)
            binding.bottomNav.visibility = View.VISIBLE
        }


    }

    private fun animator(values: Float) {
        ObjectAnimator.ofFloat(binding.bottomNav, "translationY", values).apply {
            duration = 800
            start()
        }
    }

    private fun hideBottomNav() {
        binding.bottomNav.visibility = View.GONE

    }
}