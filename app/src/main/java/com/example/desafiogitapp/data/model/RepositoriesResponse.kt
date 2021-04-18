package com.example.desafiogitapp.data.model

import com.google.gson.annotations.SerializedName

data class RepositoriesResponse (
    @SerializedName("items")
    var items: ArrayList<Items>
)

data class Items(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String,
    @SerializedName("forks")
    var forks: Int
)