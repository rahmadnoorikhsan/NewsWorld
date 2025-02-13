package com.rahmad.newsworld.utils

import com.rahmad.newsworld.BuildConfig

object Constants {
    private const val BEARER = "Bearer "
    const val AUTHORIZATION = "Authorization"
    const val ERROR_MESSAGE = "Terjadi Kesalahan"

    fun getBearer(): String {
        return BEARER + BuildConfig.API_KEY
    }

}