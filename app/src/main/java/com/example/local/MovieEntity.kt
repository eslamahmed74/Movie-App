package com.example.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "myMoviesList")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) var id: Int=0,
    var name: String,
    var imgUrl: String,
    var overView: String
)