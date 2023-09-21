package com.gsmNetworking.auth.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import org.springframework.data.redis.core.index.Indexed

/**
 * 재발급 토큰 정보를 저장하는 RedisHash 클래스 입니다.
 */
@RedisHash
data class RefreshToken(
    @Id
    val token: String,

    @Indexed
    val email: String,

    @TimeToLive
    val expirationTime: Int
)