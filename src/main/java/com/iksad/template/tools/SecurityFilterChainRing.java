package com.iksad.template.tools;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * SecurityFilterChain을 정의할 때, 기능에 따라 모듈을 쉽게 분리시켜주는 인터페이스. <br>
 * 해당 인터페이스의 구현체를 Bean Container에 넣어주면 <br>
 * 알아서 SecurityFilterChainContainer에 모인 후, 엮이는 구조로 구성했다.<br>
 */
@FunctionalInterface
public interface SecurityFilterChainRing {
    void addChainRing(HttpSecurity http) throws Exception;
}
