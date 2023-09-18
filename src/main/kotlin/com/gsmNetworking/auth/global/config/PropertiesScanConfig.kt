package com.gsmNetworking.auth.global.config

import com.gsmNetworking.auth.global.security.jwt.properties.JwtExpTimeProperties
import com.gsmNetworking.auth.global.security.jwt.properties.JwtProperties
import com.gsmNetworking.auth.global.security.oauth.properties.Oauth2Properties
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan(
    basePackageClasses = [
        JwtProperties::class,
        JwtExpTimeProperties::class,
        Oauth2Properties::class
    ]
)
class PropertiesScanConfig