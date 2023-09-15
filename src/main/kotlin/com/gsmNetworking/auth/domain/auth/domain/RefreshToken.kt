package com.gsmNetworking.auth.domain.auth.domain

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive

@RedisHash
data class RefreshToken(
    @Id
    val token: String,
    val email: String,
    @TimeToLive
    val expirationTime: Int
)