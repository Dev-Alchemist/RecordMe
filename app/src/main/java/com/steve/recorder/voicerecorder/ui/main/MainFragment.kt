package com.steve.recorder.voicerecorder.ui.main

import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.steve.recorder.R
import com.steve.recorder.databinding.FragmentMainBinding
import com.steve.recorder.voicerecorder.data.Record
import com.steve.recorder.voicerecorder.ui.activities.MainActivity
import com.steve.recorder.voicerecorder.utils.RecordState
import com.steve.recorder.voicerecorder.utils.setFullScreenWithBtmNav
import com.steve.recorder.voicerecorder.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: RecordViewModel by lazy {
        ViewModelProvider(this).get(RecordViewModel::class.java)
    }
    private var isRecording: Boolean = false
    private var title: String? = null
    private var fileName: String? = null

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

    private fun setRecordBtnClickListener() {
        if (isRecording) {
            stopRecording()
        } else {
            startRecording()
        }
    }

    private fun startRecording() {

        binding.timer.base = SystemClock.elapsedRealtime()
        binding.timer.start()
        binding.audioNameEt.isEnabled = false


        val filepath = activity?.getExternalFilesDir("/")?.absolutePath
        val formatter = SimpleDateFormat("yyyy_MM_dd_hh_ss", Locale.ENGLISH)
        title = binding.audioNameEt.text.toString()

        fileName = if (title == null || title.isNullOrEmpty()) {
            "filename" + formatter.format(Date()) + ".3gp"
        } else {
            title + ".3gp"
        }




        binding.headingText.text = "Recording started for file: ${fileName}"

        if (filepath != null) {
            viewModel.startRecording(fileName, filepath)
        } else {
            context?.toast("filepath Error")
        }

    }

    private fun stopRecording() {
        binding.timer.stop()
        binding.audioNameEt.text?.clear()
        binding.audioNameEt.isEnabled = true


        fileName = if (title == null || title.isNullOrEmpty()) {
            fileName
        } else {
            title + ".3gp"
        }


        viewModel.stopRecording(fileName)
        binding.headingText.text = "Recording stopped, file saved"

    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setFullScreenWithBtmNav()
    }

    override fun onStop() {
        super.onStop()
        if (isRecording)
            stopRecording()
    }

}