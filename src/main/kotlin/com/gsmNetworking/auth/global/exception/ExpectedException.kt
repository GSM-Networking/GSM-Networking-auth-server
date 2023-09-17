package com.gsmNetworking.auth.global.exception

import com.gsmNetworking.auth.global.exception.model.ErrorCode
import org.springframework.http.HttpStatus

/**
 * 클라이언트에게 예외 상황에 HttpStatus와 Message를 전달해주기 위해 사용되는 클래스 입니다.
 */
class ExpectedException(
    val status: HttpStatus,
    override val message: String,
    val errorCode: ErrorCode
): RuntimeException(message) {

    /**
     * 분기처리가 필요하지 않은 상황일때 사용되는 생성자 입니다.
     */
    constructor(status: HttpStatus, message: String): this(
        status = status,
        message = message,
        errorCode = ErrorCode.EXCEPTION
    )

}