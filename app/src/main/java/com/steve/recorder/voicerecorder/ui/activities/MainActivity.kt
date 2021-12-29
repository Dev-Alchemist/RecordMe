package com.steve.recorder.voicerecorder.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.steve.recorder.R
import com.steve.recorder.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.fragment)
    }

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }
}