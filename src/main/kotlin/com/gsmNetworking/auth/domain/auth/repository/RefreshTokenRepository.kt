package com.gsmNetworking.auth.domain.auth.repository

import com.gsmNetworking.auth.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

/**
 * RefreshToken Entity를 위한 Repository 인터페이스 입니다.
 */
interface RefreshTokenRepository: CrudRepository<RefreshToken, String>