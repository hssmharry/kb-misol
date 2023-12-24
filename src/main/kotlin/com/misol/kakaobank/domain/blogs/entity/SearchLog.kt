package com.misol.kakaobank.domain.blogs.entity

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@EntityListeners(AuditingEntityListener::class)
class SearchLog(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private val id: Long = 0,

        private val keyword: String,
) {
        @CreatedBy
        var createdBy: Long = 0

        @CreatedDate
        lateinit var createdAt: LocalDateTime
}