package com.gsmNetworking.auth.domain.auth.service

import com.gsmNetworking.auth.domain.auth.domain.RefreshToken
import com.gsmNetworking.auth.domain.auth.repository.AuthenticationRepository
import com.gsmNetworking.auth.domain.auth.repository.RefreshTokenRepository
import com.gsmNetworking.auth.global.exception.ExpectedException
import com.gsmNetworking.auth.global.security.jwt.TokenGenerator
import com.gsmNetworking.auth.global.security.jwt.TokenParser
import com.gsmNetworking.auth.global.security.jwt.dto.TokenResponse
import com.gsmNetworking.auth.global.security.jwt.properties.JwtExpTimeProperties
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * 로그인 연장을 위한 토큰 재발급을 해주는 서비스 클래스 입니다.
 */
@Service
@Transactional(rollbackFor = [Exception::class])
class ReissueTokenService(
    private val tokenParser: TokenParser,
    private val tokenGenerator: TokenGenerator,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val authenticationRepository: AuthenticationRepository,
    private val jwtExpTimeProperties: JwtExpTimeProperties
) {

    /**
     * 유효한 RefreshToken 이라면 Token을 리턴하고, 유효하지 않은 RefreshToken 이라면 예외를 던집니다.
     *
     * @param token 로그인 연장에 필요한 재발급(RefreshToken) 토큰
     * @throws ExpectedException 이 터지는 조건은 아래와 같다.
     *      1. RefreshToken Entity에 저장되지 않은 RefreshToken일 경우
     *      2. RefreshToken의 Subject가 저장되지 않은 경우
     *      3. 유효하지 않은 Token Prefix로 요청 한 경우
     * @return TokenResponse
     */
    fun execute(token: String): TokenResponse {
        val parsedToken = tokenParser.parseRefreshToken(token)
        val refreshToken = refreshTokenRepository.findByIdOrNull(parsedToken)
            ?: throw ExpectedException(HttpStatus.NOT_FOUND, "존재하지 않는 refresh token 입니다.")
        val authentication = authenticationRepository.findByEmail(refreshToken.email)
            ?: throw ExpectedException(HttpStatus.NOT_FOUND, "refresh token subject인 email을 찾을 수 없습니다.")
        val tokenDto = tokenGenerator.generateToken(refreshToken.email, authentication.authority)
        saveRefreshToken(tokenDto.refreshToken, authentication.email)
        deleteBeforeRefreshToken(refreshToken)
        return tokenDto
    }

    private fun saveRefreshToken(token: String, email: String) {
        val refreshToken = RefreshToken(
            token = token,
            email = email,
            expirationTime = jwtExpTimeProperties.refreshExp
        )
        refreshTokenRepository.save(refreshToken)
    }

    private fun deleteBeforeRefreshToken(refreshToken: RefreshToken) {
        refreshTokenRepository.delete(refreshToken)
    }

}