package com.gsmNetworking.auth.domain.auth.repository

import com.gsmNetworking.auth.domain.auth.domain.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, String>