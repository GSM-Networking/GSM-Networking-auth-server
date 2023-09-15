package com.gsmNetworking.auth.domain.auth.domain

/**
 * 사용자의 권한을 판별하는 enum 클래스 입니다.
 *
 * @author 김성길
 * @since 1.0.0
 */
enum class Authority {

    ROLE_UNAUTHENTICATED,
    ROLE_USER,
    ROLE_ADMIN

}