package com.steve.recorder.voicerecorder.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.steve.recorder.R
import com.steve.recorder.databinding.FragmentSplashBinding
import com.steve.recorder.voicerecorder.utils.CoroutinesHelper

class SplashFragment: Fragment(){

    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSplashBinding.inflate(inflater, container, false)

        goToMain()

        return binding.root
    }

    private fun goToMain() {
        CoroutinesHelper.delayWithMain(3000L) {
            findNavController().navigate(R.id.action_splashFragment_to_mainFragment)
        }
    }
}