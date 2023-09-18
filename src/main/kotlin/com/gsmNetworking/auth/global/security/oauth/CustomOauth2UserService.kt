package com.gsmNetworking.auth.global.security.oauth

import com.gsmNetworking.auth.domain.auth.domain.Authentication
import com.gsmNetworking.auth.domain.auth.domain.Authority
import com.gsmNetworking.auth.domain.auth.repository.AuthenticationRepository
import com.gsmNetworking.auth.global.security.oauth.dto.UserInfo
import com.gsmNetworking.auth.global.security.oauth.properties.Oauth2Properties
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CustomOauth2UserService(
    private val authenticationRepository: AuthenticationRepository
): OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val delegateOauth2UserService = DefaultOAuth2UserService()
        val oauth2User = delegateOauth2UserService.loadUser(userRequest)

        val providerId = oauth2User.name
        val email = oauth2User.attributes[Oauth2Properties.EMAIL].toString()
        val authority = getAuthority(email, providerId).authority
        val userNameAttributeName = userRequest.clientRegistration
            .providerDetails.userInfoEndpoint.userNameAttributeName
        val attributes = mapOf(
            "sub" to email,
            "provider_id" to providerId,
            "authority" to authority,
            "last_login_time" to LocalDateTime.now()
        )
        val authorities = mutableListOf(SimpleGrantedAuthority(authority.name))

        return UserInfo(authorities, attributes, userNameAttributeName)
    }

    private fun getAuthority(email: String, providerId: String): Authentication =
        authenticationRepository.findByProviderId(providerId) ?: saveAuthentication(email, providerId)

    private fun saveAuthentication(email: String, providerId: String): Authentication {
        val authentication = Authentication(
            email = email,
            providerId = providerId,
            authority = Authority.ROLE_UNAUTHENTICATED
        )
        return authenticationRepository.save(authentication)
    }

}