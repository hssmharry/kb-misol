package com.misol.kakaobank.infrastructure.dto

data class KaKaoSearchResponse(
        val documents: List<BlogDocument>,
        val meta: Meta,
)

data class BlogDocument(
        val blogname: String?,
        val contents: String?,
        val datetime: String?,
        val thumbnail: String?,
        val title: String?,
        val url: String?,
)


data class Meta(
        val is_end: Boolean,
        val pageable_count: Int,
        val total_count: Long,
)