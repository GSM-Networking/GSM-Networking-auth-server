package com.gsmNetworking.auth.domain.auth.repository

import com.gsmNetworking.auth.domain.auth.domain.Authentication
import org.springframework.data.repository.CrudRepository

/**
 * Authentication Entity를 위한 Repository 인터페이스 입니다.
 *
 * @author 김성길
 * @since 1.0.0
 */
interface AuthenticationRepository: CrudRepository<Authentication, String> {

    fun findByProviderId(providerId: String): Authentication?

}