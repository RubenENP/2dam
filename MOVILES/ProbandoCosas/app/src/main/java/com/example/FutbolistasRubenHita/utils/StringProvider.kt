package com.example.ProbandoCosas.utils

import android.content.Context
import androidx.annotation.StringRes

class StringProvider(val context: Context) {
    companion object {
        fun instance(context: Context): StringProvider = StringProvider(context)
    }

    fun getString(@StringRes stringRedId: Int): String {
        return context.getString(stringRedId)
    }
}