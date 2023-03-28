package com.example.movieapp.bottomNav.accountFragment

import com.example.movieapp.R
import com.example.movieapp.utils.BaseAdapter

class ListSettingAdapter(list:List<SettingData>):BaseAdapter<SettingData>(list) {
    override val layoutId: Int
        get() = R.layout.setting_item_layout
}