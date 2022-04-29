package com.goonandroid.appselect
import android.graphics.Bitmap
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class Movie(

    @SerializedName("display_title")
    val title: String?,
    @SerializedName("multimedia")
    val multimedia:Multimedia?,
    @SerializedName("summary_short")
    val description: String?,

    ) : Parcelable{
    constructor() : this("",null,"")

}
@Parcelize
data class Multimedia(
    @SerializedName("src")
    val poster:String?

        ): Parcelable{
    constructor() : this("")}

