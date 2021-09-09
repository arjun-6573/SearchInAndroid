package com.example.codeinandroid.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.codeinandroid.R
import com.example.codeinandroid.databinding.ActivityMainBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var binding: ActivityMainBinding
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navMain) as NavHostFragment
        navController = navHostFragment.navController

        initToolbar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.popBackStack()
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolBar)
        setupActionBarWithNavController(navController)
    }
}