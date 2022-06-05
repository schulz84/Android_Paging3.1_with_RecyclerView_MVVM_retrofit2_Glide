package com.goonandroid.appselect.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.goonandroid.appselect.Movie
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieResponse(

    @SerializedName("results")
    val movies: List<Movie>,

    @SerializedName("has_more")
    val has_more: Boolean,
)
: Parcelable {
    constructor() : this(mutableListOf(),true)
}