package com.iksad.template.tools;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * SecurityFilterChainUnit을 한 곳으로 모아 HttpSecurity로 만드는 Container.<br>
 * addChainRing 메서드를 이용해서 SecurityFilterChainUnit 구현체들을 엮어서 결과롤 내놓는다.<br>
 */
@Component
@RequiredArgsConstructor
public class SecurityFilterChainRingContainer implements SecurityFilterChainRing {
    private final Collection<SecurityFilterChainRing> securityFilterChainUnits;

    public void addChainRing(HttpSecurity http) throws Exception {
        for (SecurityFilterChainRing chainUnit : securityFilterChainUnits) {
            chainUnit.addChainRing(http);
        }
    }
}
