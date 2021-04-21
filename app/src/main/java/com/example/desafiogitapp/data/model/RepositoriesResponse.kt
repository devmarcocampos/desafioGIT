package com.example.desafiogitapp.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class RepositoriesResponse (
    @SerializedName("items")
    var repositories: ArrayList<Repository>
)

data class Repository(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("stargazers_count")
    var stargazersCount: Int,
    @SerializedName("forks")
    var forks: Int,
    @SerializedName("owner")
    var owner: Owner
) : Serializable

data class Owner(
    @SerializedName("id")
    var id: Int,
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatar_url: String
) : Serializable