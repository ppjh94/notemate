package com.test.notemate.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/*Srping Security 설정 비활성화*/
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class SecurityBeanConfig {

	/*Srping Security 설정 임시 비활성화*/
	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http .csrf(AbstractHttpConfigurer::disable)
	 * .formLogin(AbstractHttpConfigurer::disable)
	 * .httpBasic(AbstractHttpConfigurer::disable) .authorizeHttpRequests(auth ->
	 * auth.anyRequest().permitAll()); //모든 경로 허용
	 * 
	 * return http.build(); }
	 */
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
