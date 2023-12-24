package com.misol.kakaobank.application.blog

import com.misol.kakaobank.application.blog.factory.SearchExternalServiceFactory
import com.misol.kakaobank.application.blog.model.BlogSearchRequestModel
import com.misol.kakaobank.application.blog.model.PopularKeywordResponseModel
import com.misol.kakaobank.application.blog.model.SearchResponseItemModel
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import com.misol.kakaobank.infrastructure.repository.SearchLogRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchService(
        val searchLogRepository: SearchLogRepository,
        val searchExternalServiceFactory: SearchExternalServiceFactory,
) {

    @Transactional
    fun search(request: BlogSearchRequestModel): Page<SearchResponseItemModel> {
        // KAKAO API가 실패하면 NAVER API로 대체
        val apiResponse = runCatching {
            searchExternalServiceFactory.getSearchService(SearchServiceType.KAKAO).search(request)
        }.getOrElse {
            searchExternalServiceFactory.getSearchService(SearchServiceType.NAVER).search(request)
        }

        if (request.page == 1) {
            // 로그 저장 (페이지가 2 이상인 경우에는 새로운 검색이라고 판단하지 않음)
            searchLogRepository.save(request.toLog())
        }

        return PageImpl(apiResponse.items, PageRequest.of(request.page, request.size), apiResponse.totalElement)
    }

    @Transactional(readOnly = true)
    fun popular(): List<PopularKeywordResponseModel> = searchLogRepository.findTop10ByOrderByCountDesc()
            .map { PopularKeywordResponseModel.of(it) }

}