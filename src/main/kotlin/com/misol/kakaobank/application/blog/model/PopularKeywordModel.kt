package com.misol.kakaobank.application.blog.model

import com.misol.kakaobank.domain.blogs.entity.PopularInfoProjection
import io.swagger.v3.oas.annotations.media.Schema

data class PopularKeywordResponseModel(
        @Schema(description = "검색 키워드", example = "카카오뱅크")
        val keyword: String,
        @Schema(description = "검색 횟수", example = "3")
        val count: Int,
) {
    companion object {
        fun of(info: PopularInfoProjection): PopularKeywordResponseModel = PopularKeywordResponseModel(
                keyword = info.keyword,
                count = info.count,
        )
    }
}