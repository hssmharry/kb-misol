package com.misol.kakaobank.application.blog

import com.misol.kakaobank.application.blog.factory.SearchExternalService
import com.misol.kakaobank.application.blog.model.BlogSearchRequestModel
import com.misol.kakaobank.application.blog.model.SearchResponseModel
import com.misol.kakaobank.configuration.ExternalConfig
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import com.misol.kakaobank.infrastructure.dto.NaverSearchResponse
import com.misol.kakaobank.infrastructure.webclient.SearchClient
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class NaverSearchService(
        val externalConfig: ExternalConfig,
        val searchClient: SearchClient,
) : SearchExternalService {
    override fun getServiceType(): SearchServiceType = SearchServiceType.NAVER

    override fun search(request: BlogSearchRequestModel): SearchResponseModel {
        val naverConfig = externalConfig.naver
        val result = searchClient.apiSearch(
                defaultUrl = naverConfig.defaultUrl!!,
                uriBuilder = UriComponentsBuilder.fromUriString(naverConfig.defaultUrl!!)
                        .path(naverConfig.search!!)
                        .query("query=${URLEncoder.encode(request.keyword, StandardCharsets.UTF_8.toString())}")
                        .query("sort=${request.sortingType.naver}")
                        .query("display=${request.size}")
                        .query("start=${request.page}")
                        .build().toUri(),
                headers = { headers ->
                    headers.set("X-Naver-Client-Id", naverConfig.clientId)
                    headers.set("X-Naver-Client-Secret", naverConfig.clientSecret)
                },
                responseType = NaverSearchResponse::class.java,
        )

        return SearchResponseModel.of(result)
    }
}