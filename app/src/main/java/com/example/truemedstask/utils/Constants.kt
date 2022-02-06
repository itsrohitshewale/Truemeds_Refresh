package com.example.truemedstask.utils

class Constants {
    enum class ENVIRONMENT {
        PROD, UAT, DEV
    }

    companion object {
        const val CONNECTION_TIMEOUT = 25L

        const val BASE_URL = "https://stage-services.truemeds.in/"
        const val ARTICLE_URL = "ArticleService/getArticleListing"
    }
}