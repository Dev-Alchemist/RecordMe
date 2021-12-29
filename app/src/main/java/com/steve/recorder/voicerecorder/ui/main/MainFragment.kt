package com.steve.recorder.voicerecorder.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.steve.recorder.R
import com.steve.recorder.databinding.FragmentMainBinding
import com.steve.recorder.voicerecorder.data.Record
import com.steve.recorder.voicerecorder.utils.RecordState
import com.steve.recorder.voicerecorder.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: RecordViewModel by lazy {
        ViewModelProvider(this).get(RecordViewModel::class.java)
    }
    private var isRecording: Boolean = false
    private var title: String? = null
    private var filePath: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        subscribe()

        return binding.root
    }

    private fun subscribe() {
        viewModel.recordState.observe(viewLifecycleOwner, {recordState ->
            when (recordState) {
                is RecordState.Recording -> {
                    isRecording = true
                    switchButtonDrawable(isRecording)
                }
                is RecordState.Done<Record> -> {
                    isRecording = false
                    switchButtonDrawable(isRecording)

                    context?.toast("File saved successfully")
                }
                is RecordState.Error -> {}
            }
        })
    }

    private fun switchButtonDrawable(recording: Boolean) {
        if (recording) {
            binding.recordBtn.setImageDrawable(
                activity?.let {
                    ContextCompat.getDrawable(it, R.drawable.ic_baseline_mic_off_24)
                }
            )
        } else {
            binding.recordBtn.setImageDrawable(
                activity?.let {
                    ContextCompat.getDrawable(it, R.drawable.ic_baseline_mic_24)
                }
            )
        }
    }
}