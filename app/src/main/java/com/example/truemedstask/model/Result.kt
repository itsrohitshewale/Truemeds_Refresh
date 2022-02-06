package com.example.truemedstask.model

import com.google.gson.annotations.SerializedName

data class Result (

    @SerializedName("category" ) var category : ArrayList<Category> = arrayListOf(),
    @SerializedName("article"  ) var article  : ArrayList<Article>  = arrayListOf()

)