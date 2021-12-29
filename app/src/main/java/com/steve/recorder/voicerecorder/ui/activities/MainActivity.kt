package com.steve.recorder.voicerecorder.ui.activities

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.steve.recorder.R
import com.steve.recorder.databinding.ActivityMainBinding
import com.steve.recorder.voicerecorder.utils.setFullScreenForNotch
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val navController: NavController by lazy {
        findNavController(R.id.fragment)
    }

    private lateinit var mainBinding: ActivityMainBinding
    private val permList: Array<String> = arrayOf(
        RECORD_AUDIO,
        WRITE_EXTERNAL_STORAGE
    )

    companion object {
        private const val PERMISSION_CODE = 121
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        if (!checkForPermission(RECORD_AUDIO)) {
            askPermission(permList)

        }
        if (!checkForPermission(WRITE_EXTERNAL_STORAGE)) {
            askPermission(permList)
        }
    }

    override fun onStart() {
        super.onStart()
        setFullScreenForNotch()
    }

    private fun checkForPermission(permission: String) : Boolean {

        return ActivityCompat.checkSelfPermission(
            this@MainActivity,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun askPermission(permList: Array<String>) {
        ActivityCompat.requestPermissions(this@MainActivity, permList, PERMISSION_CODE)
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }
}