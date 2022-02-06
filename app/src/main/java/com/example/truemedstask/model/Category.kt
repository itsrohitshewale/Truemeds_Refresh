package com.example.truemedstask.model

import com.google.gson.annotations.SerializedName

data class Category (

    @SerializedName("name" ) var name : String? = null,
    @SerializedName("id"   ) var id   : Int?    = null

)