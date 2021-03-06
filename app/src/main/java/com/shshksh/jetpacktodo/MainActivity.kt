package com.shshksh.jetpacktodo

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.shshksh.jetpacktodo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val host = supportFragmentManager
            .findFragmentById(R.id.container_root) as NavHostFragment? ?: return
        val navController = host.navController

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.addFragment -> {
                    binding.toolbarTop.visibility = View.GONE
                }
                else -> {
                    binding.toolbarTop.visibility = View.VISIBLE
                }
            }
        }

        binding.toolbarTop.setupWithNavController(navController, appBarConfiguration)
    }

    fun obtainFactory() = (application as App).viewModelFactory
}