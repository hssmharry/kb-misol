package com.misol.kakaobank.infrastructure.dto


data class NaverSearchResponse(
        val lastBuildDate: String,
        val total: Long,
        val start: Int,
        val display: Int,
        val items: List<BlogPost>
)

data class BlogPost(
        val title: String,
        val link: String,
        val description: String,
        val bloggername: String,
        val bloggerlink: String,
        val postdate: String
)