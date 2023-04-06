package com.kraievskyi.task.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests(authRequests -> authRequests
                        .requestMatchers("/*.jpg").permitAll()
                        .requestMatchers("/add-book-to-user")
                        .hasAuthority("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/")
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout").permitAll()
                )
                .build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf()
//                .disable()
//                .authorizeHttpRequests()
//                .requestMatchers(HttpMethod.POST, "/users")
//                .permitAll()
//                .requestMatchers(HttpMethod.GET, "/books/{id}").hasAuthority("USER")
//                .anyRequest()
//                .authenticated()
//                .requestMatchers( "/books", "/users")
//                .permitAll()
//                .requestMatchers(HttpMethod.POST, "books").hasAuthority("ADMIN")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .httpBasic();
////                .and()
////                .logout()
////                .logoutUrl("auth/logout");
//
//        return http.build();
//    }
}
