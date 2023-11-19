package com.example.foodappcleanarchitecutre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.codingwithmitch.food2forkcompose.presentation.util.ConnectionLiveData
import com.example.foodappcleanarchitecutre.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var connectionLiveData: LiveData<Boolean>

    lateinit var navController :NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.lifecycleOwner = this
//        connectionLiveData = ConnectionLiveData.create(applicationContext)
        navController = this.findNavController(R.id.host_fragment)
        binding.btmNav.setupWithNavController(navController)
        observeInternetConnection()

    }
    private fun observeInternetConnection() {
        // Observe changes in network availability
        connectionLiveData.observe(this) { isNetworkAvailable ->
            if (isNetworkAvailable) {
                // Network is available
                Log.i("main","network working")
                navController.popBackStack()

            } else {
                // Network is not available
                Log.i("main","network not working")
                navController.navigate(R.id.networkErrorFragment)
            }
        }
    }

}