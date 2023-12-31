package com.example.my_media.util

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes

interface ContextProvider {
    fun getSharedPreferences(): SharedPreferences

    fun getString(@StringRes stringRes: Int): String
}

class ContextProviderImpl(
    private val context: Context
) : ContextProvider {
    override fun getSharedPreferences(): SharedPreferences {
        return context.getSharedPreferences("GET_SHARED_PREFERENCES", Context.MODE_PRIVATE)
    }

    override fun getString(stringRes: Int): String {
        return context.getString(stringRes)
    }
}