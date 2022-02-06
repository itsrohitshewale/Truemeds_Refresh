package com.example.truemedstask.model

import com.google.gson.annotations.SerializedName

data class Article (
    @SerializedName("description"  ) var description  : String? = null,
    @SerializedName("categoryName" ) var categoryName : String? = null,
    @SerializedName("type"         ) var type         : Int?    = null,
    @SerializedName("categoryId"   ) var categoryId   : Int?    = null,
    @SerializedName("author"       ) var author       : String? = null,
    @SerializedName("name"         ) var name         : String? = null,
    @SerializedName("id"           ) var id           : Int?    = null,
    @SerializedName("url"          ) var url          : String? = null,
    @SerializedName("createdOn"    ) var createdOn    : String? = null,
    @SerializedName("image"        ) var image        : String? = null,
    @SerializedName("articleTime"  ) var articleTime  : Int?    = null,
    @SerializedName("ranking"      ) var ranking      : Int?    = null

)