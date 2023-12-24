package com.misol.kakaobank.application.blog

import com.misol.kakaobank.application.blog.factory.SearchExternalServiceFactory
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import com.misol.kakaobank.presentation.blog.model.BlogSearchRequest
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SearchTest @Autowired constructor(
        val searchExternalServiceFactory: SearchExternalServiceFactory,
) {

    @Test
    @Transactional
    fun `검색 - reuqest size 갯수만큼 조회되어야 한다`() {
        val searchSizes = intArrayOf(7, 10, 13)
        for (searchSize in searchSizes) {
            val result = searchExternalServiceFactory.getSearchService(SearchServiceType.KAKAO)
                    .search(getDefaultRequest(searchSize = searchSize))
            assert(result.items.size == searchSize)
        }
    }

    @Test
    @Transactional
    fun `검색 - 카카오 API 호출이 실패하면 NAVER API로 대체되어야 한다`() {
        val kakaoResult = runCatching {
            searchExternalServiceFactory.getSearchService(SearchServiceType.KAKAO).search(getDefaultRequest())
        }.getOrElse {
            searchExternalServiceFactory.getSearchService(SearchServiceType.NAVER).search(getDefaultRequest())
        }

        val naverResult = runCatching {
            searchExternalServiceFactory.getSearchService(SearchServiceType.KAKAO).search(getDefaultRequest())
            throw Exception()
        }.getOrElse {
            searchExternalServiceFactory.getSearchService(SearchServiceType.NAVER).search(getDefaultRequest())
        }

        assert(SearchServiceType.KAKAO == kakaoResult.searchServiceType)
        assert(SearchServiceType.NAVER == naverResult.searchServiceType)
    }

    private fun getDefaultRequest(searchSize: Int = 10) = BlogSearchRequest(
            keyword = "카카오뱅크",
            size = searchSize,
    ).toModel()
}