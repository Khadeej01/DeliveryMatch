package org.deliverymatch.backend.security;


import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
            .csrf().disable()
            .authorizeRequests()
            .antMatchers("/api/auth/**").permitAll()
            .antMatchers("/api/trajets/**").hasRole("CONDUCTEUR")
            .antMatchers("/api/demandes/**").hasRole("EXPEDITEUR")
            .anyRequest().authenticated()
            .and()
            .addFilter(new JwtAuthenticationFilter(authenticationManager(http.getSharedObject(AuthenticationManagerBuilder.class))))
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
}