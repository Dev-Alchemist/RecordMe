package com.steve.recorder.voicerecorder.ui.records

import androidx.lifecycle.ViewModel
import com.steve.recorder.voicerecorder.data.RecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RecordsListViewModel
@Inject constructor(private val repository: RecordRepository) :
ViewModel() {
}