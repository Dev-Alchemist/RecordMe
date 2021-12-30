package com.steve.recorder.voicerecorder.di

import com.steve.recorder.voicerecorder.adapters.VoiceRecordAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent


@InstallIn(ActivityComponent::class)
@Module
class AdaptersModule {

    @Provides
    fun providesVoiceAdapter(): VoiceRecordAdapter {
        return VoiceRecordAdapter()
    }
}