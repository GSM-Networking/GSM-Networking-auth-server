package com.gsmNetworking.auth.domain.auth.domain

/**
 * 사용자의 권한을 판별하는 enum 클래스 입니다.
 */
enum class Authority(
    val authority: String
) {

    ROLE_UNAUTHENTICATED("UNAUTHENTICATED"),
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN")

}