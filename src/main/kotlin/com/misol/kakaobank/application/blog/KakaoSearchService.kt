package com.misol.kakaobank.application.blog

import com.misol.kakaobank.application.blog.factory.SearchExternalService
import com.misol.kakaobank.application.blog.model.BlogSearchRequestModel
import com.misol.kakaobank.application.blog.model.SearchResponseModel
import com.misol.kakaobank.configuration.ExternalConfig
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import com.misol.kakaobank.infrastructure.dto.KaKaoSearchResponse
import com.misol.kakaobank.infrastructure.webclient.SearchClient
import org.springframework.stereotype.Service
import org.springframework.web.util.UriComponentsBuilder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class KakaoSearchService(
        val externalConfig: ExternalConfig,
        val searchClient: SearchClient,
) : SearchExternalService {
    override fun getServiceType(): SearchServiceType = SearchServiceType.KAKAO

    override fun search(request: BlogSearchRequestModel): SearchResponseModel {
        val kakaoConfig = externalConfig.kakao
        val result = searchClient.apiSearch(
                defaultUrl = kakaoConfig.defaultUrl!!,
                uriBuilder = UriComponentsBuilder.fromUriString(kakaoConfig.defaultUrl!!)
                        .path(kakaoConfig.search!!)
                        .query("query=${URLEncoder.encode(request.keyword, StandardCharsets.UTF_8.toString())}")
                        .query("sort=${request.sortingType.kakao}")
                        .query("page=${request.page}")
                        .query("size=${request.size}")
                        .build().toUri(),
                headers = { headers -> headers.set("Authorization", "KakaoAK ${kakaoConfig.key}") },
                responseType = KaKaoSearchResponse::class.java,
        )

        return SearchResponseModel.of(result)
    }
}