package com.example.finalproject.data.model

import android.transition.Visibility
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import org.intellij.lang.annotations.Language

@JsonClass(generateAdapter = true)
data class GithubRepository(
    val id:Long,
    val name: String,
    val private:Boolean,
    val owner: GithubRepoOwner,
    val description:String?,
    @Json(name = "created_at")
    val createdAt:String,
    @Json(name="stargazers_count")
    val stars:Int,
    val language: String?=null,
    val visibility: String
)
@JsonClass (generateAdapter = true)
data class GithubRepoOwner(
    @Json(name = "id")
    val userId:Long,
    @Json(name="login")
    val userName:String,
    @Json(name = "avatar_url")
    val avatarUrl:String,
    @Json(name = "html_url")
    val htmlUrl:String,
    val type:String,



)