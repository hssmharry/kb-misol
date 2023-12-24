package com.misol.kakaobank.application.blog.model

import com.misol.kakaobank.domain.blogs.entity.SearchLog
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import com.misol.kakaobank.domain.blogs.enums.SortingType
import com.misol.kakaobank.infrastructure.dto.BlogDocument
import com.misol.kakaobank.infrastructure.dto.BlogPost
import com.misol.kakaobank.infrastructure.dto.KaKaoSearchResponse
import com.misol.kakaobank.infrastructure.dto.NaverSearchResponse
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

data class BlogSearchRequestModel(
        val keyword: String,
        val page: Int,
        val size: Int,
        val sortingType: SortingType,
) {
    fun toLog(): SearchLog = SearchLog(
            keyword = keyword,
    )
}

data class SearchResponseModel(
        val searchServiceType: SearchServiceType,
        val totalElement: Long = 0,
        val items: List<SearchResponseItemModel>,
) {
    companion object {
        fun of(kakaoResponse: KaKaoSearchResponse?) = SearchResponseModel(
                searchServiceType = SearchServiceType.KAKAO,
                items = kakaoResponse?.documents?.map { SearchResponseItemModel.of(it) } ?: emptyList(),
                totalElement = kakaoResponse?.meta?.total_count ?: 0,

                )

        fun of(naverResponse: NaverSearchResponse?) = SearchResponseModel(
                searchServiceType = SearchServiceType.NAVER,
                items = naverResponse?.items?.map { SearchResponseItemModel.of(it) } ?: emptyList(),
                totalElement = naverResponse?.total ?: 0,
        )
    }
}

data class SearchResponseItemModel(

        @Schema(description = "블로그 내용 요약", example = "카카오뱅크의 대출 상품을 소개합니다.")
        var contents: String?,
        @Schema(description = "블로그 작성일", example = "2021-01-01")
        val createdAt: LocalDate? = null,
        @Schema(description = "블로그 제목", example = "카카오뱅크 대출 상품 소개")
        val title: String?,
        @Schema(description = "블로그 url (링크)", example = "https://blog.naver.com/~~~")
        val url: String?,
) {
    companion object {
        fun of(item: BlogPost) = SearchResponseItemModel(
                contents = item.description,
                createdAt = LocalDate.parse(item.postdate, DateTimeFormatter.ofPattern("yyyyMMdd")),
                title = item.title,
                url = item.link,
        )

        fun of(item: BlogDocument) = SearchResponseItemModel(
                contents = item.contents,
                createdAt = OffsetDateTime.parse(item.datetime).toLocalDate(),
                title = item.title,
                url = item.url,
        )
    }
}