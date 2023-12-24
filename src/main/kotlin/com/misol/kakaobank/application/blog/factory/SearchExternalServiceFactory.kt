package com.misol.kakaobank.application.blog.factory

import com.misol.kakaobank.domain.blogs.enums.SearchServiceType
import org.springframework.stereotype.Component

@Component
class SearchExternalServiceFactory(serviceList: List<SearchExternalService>) {
    private val serviceMap = mutableMapOf<SearchServiceType, SearchExternalService>()

    init {
        serviceList.forEach { serviceMap[it.getServiceType()] = it }
    }

    fun getSearchService(serviceType: SearchServiceType): SearchExternalService {
        return serviceMap[serviceType] ?: throw IllegalArgumentException("not found type")
    }
}