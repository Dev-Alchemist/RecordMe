package com.steve.recorder.voicerecorder.utils

import android.os.Build

fun isAboveR():Boolean{
    return Build.VERSION.SDK_INT> Build.VERSION_CODES.R
}
fun isAboveP():Boolean{
    return Build.VERSION.SDK_INT> Build.VERSION_CODES.P
}