package com.misol.kakaobank.application.blog.factory

import com.misol.kakaobank.application.blog.model.BlogSearchRequestModel
import com.misol.kakaobank.application.blog.model.SearchResponseModel
import com.misol.kakaobank.domain.blogs.enums.SearchServiceType

interface SearchExternalService {
    fun getServiceType(): SearchServiceType
    fun search(request: BlogSearchRequestModel): SearchResponseModel
}