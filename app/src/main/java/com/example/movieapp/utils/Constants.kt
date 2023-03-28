package com.example.movieapp.utils

import com.example.movieapp.R
import com.example.movieapp.bottomNav.accountFragment.SettingData
import com.google.android.gms.common.util.CollectionUtils

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "cd4dc72a72d689ebae53eb4ab14dc07f"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    val SETTING_DATA_LIST_ITEM = listOf<SettingData>(
        SettingData("change password ", R.drawable.password),
        SettingData("Notification", R.drawable.notification),
        SettingData("Invite friends ", R.drawable.refer),
        SettingData("terms and condition ", R.drawable.terms),
        SettingData("Log out", R.drawable.log_out)
    )
}
