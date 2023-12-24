package com.misol.kakaobank.presentation.blog.model

import com.misol.kakaobank.application.blog.model.BlogSearchRequestModel
import com.misol.kakaobank.domain.blogs.enums.SortingType
import com.misol.kakaobank.exception.ValidEnum
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.NotEmpty

data class BlogSearchRequest(
        @field: NotEmpty(message = "검색어는 공백일 수 없습니다.")
        @Schema(description = "검색어", example = "카카오뱅크")
        val keyword: String,

        @field: ValidEnum(enumClass = SortingType::class, message = "올바른 정렬타입이 아닙니다.")
        @Schema(description = "정렬 타입", example = "RECENCY")
        val sortingType: SortingType = SortingType.RECENCY,

        @field: Max(value = 100, message = "페이지는 100을 넘을 수 없습니다.")
        @Schema(description = "페이지 번호", example = "1")
        val page: Int = 1,

        @field: Max(value = 100, message = "사이즈는 100을 넘을 수 없습니다.")
        @Schema(description = "페이지 사이즈", example = "10")
        val size: Int = 10,
) {
    fun toModel(): BlogSearchRequestModel = BlogSearchRequestModel(
            keyword = keyword,
            sortingType = sortingType,
            page = page,
            size = size,
    )
}