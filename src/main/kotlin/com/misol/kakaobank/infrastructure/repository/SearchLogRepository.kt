package com.misol.kakaobank.infrastructure.repository

import com.misol.kakaobank.domain.blogs.entity.PopularInfoProjection
import com.misol.kakaobank.domain.blogs.entity.SearchLog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface SearchLogRepository : JpaRepository<SearchLog, Long> {
    @Query("SELECT s.keyword AS keyword, COUNT(s.keyword) AS count FROM SearchLog s GROUP BY s.keyword ORDER BY COUNT(s.keyword) DESC, s.keyword DESC LIMIT 10")
    fun findTop10ByOrderByCountDesc(): List<PopularInfoProjection>
}