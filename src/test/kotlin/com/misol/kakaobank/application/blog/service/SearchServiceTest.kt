package com.misol.kakaobank.application.blog.service

import com.misol.kakaobank.application.blog.SearchService
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import com.misol.kakaobank.presentation.blog.model.BlogSearchRequest
import jakarta.transaction.Transactional
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SearchServiceTest @Autowired constructor(
        val searchService: SearchService,
) {

    @Test
    @Transactional
    fun search() {
        // given
        val request = getDefaultRequest()
        // when
        val result = searchService.search(request)
        // then
        assert(result.items.size == request.size)
        assert(result.searchServiceType == SearchServiceType.KAKAO)
    }

    @Test
    @Transactional
    fun popular() {
        // given
        val requestMap = mapOf(
                "카카오뱅크" to 3,
                "카카오페이" to 11,
                "카카오" to 1,
                "커피" to 4,
                "뉴진스" to 12,
                "세헤라자데" to 8,
                "크리스마스" to 2,
                "아이브" to 2,
                "장원영" to 11,
                "개구리" to 9,
                "젤리" to 1,
                "비타민" to 7,
        )

        // when
        requestMap.forEach {
            for (i in 1..it.value) {
                searchService.search(getDefaultRequest(it.key))
            }
        }

        // then
        // 10번 이상 검색해도 상위 10개만 조회되어야 한다.
        assert(searchService.popular().size == 10)

        // 각 키워드의 검색 횟수가 맞는지 확인
        searchService.popular().forEach {
            assert(it.count == requestMap[it.keyword])
        }
    }

    private fun getDefaultRequest(keyword: String = "카카오뱅크", searchSize: Int = 10) = BlogSearchRequest(
            keyword = keyword,
            size = searchSize,
    ).toModel()
}