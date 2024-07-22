package com.r3s.warehouse.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties("app")
public class AppProperties {

    private long refreshExpiration = 1296000000;// 15 days in ms;
//    private long refreshExpiration = 129;// 15 days in ms;
    private long accessTokenExpiration = 900000; //15 min in ms
    private String secretKey = "jzMtnkhpEgG37ZB1JZmdytAbIT4VOIPqUpIpQqbxZpQZ7vTGFC0BRxcN7BwgeNe0";

    private String[] WHITE_LIST_URL = {
            "/v1/auth/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};
}
