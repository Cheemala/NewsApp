package com.cheemala.newsapp.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import com.cheemala.newsapp.R
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("author")
    var author: String?,
    @SerializedName("content")
    var content: String?,
    @SerializedName("description")
    var description: String?,
    @SerializedName("publishedAt")
    var publishedAt: String?,
    @SerializedName("source")
    var source: Source,
    @SerializedName("title")
    var title: String?,
    @SerializedName("url")
    var url: String?,
    @SerializedName("urlToImage")
    var urlToImage: String?
): Serializable

@BindingAdapter("displayImage")
fun displayImgFromUrl(imgVw:ImageView,imgUrl:String?){
    Glide.with(imgVw.context).load(imgUrl)
        .placeholder((imgVw.context).getDrawable(R.drawable.load))
        .into(imgVw)
}