package com.gsmNetworking.auth.global.exception

import org.springframework.http.HttpStatus

/**
 * 클라이언트에게 예외 상황에 HttpStatus와 Message를 전달해주기 위해 사용되는 클래스 입니다.
 *
 * @author 김성길
 * @since 1.0.0
 */
class ExpectedException(
    val status: HttpStatus,
    override val message: String
): RuntimeException(message)