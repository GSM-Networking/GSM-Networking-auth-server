package com.gsmNetworking.auth.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

/**
 * 재발급 토큰 정보를 저장하는 RedisHash 클래스 입니다.
 *
 * @author 김성길
 * @since 1.0.0
 */
@RedisHash
data class RefreshToken(
    @Id
    val token: String,
    val email: String,
    @TimeToLive
    val expirationTime: Int
)