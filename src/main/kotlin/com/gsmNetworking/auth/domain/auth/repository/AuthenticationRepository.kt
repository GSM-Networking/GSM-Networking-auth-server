package com.gsmNetworking.auth.domain.auth.repository

import com.gsmNetworking.auth.domain.auth.domain.Authentication
import org.springframework.data.repository.CrudRepository

interface AuthenticationRepository: CrudRepository<Authentication, String> {

    fun findByProviderId(providerId: String): Authentication?

}