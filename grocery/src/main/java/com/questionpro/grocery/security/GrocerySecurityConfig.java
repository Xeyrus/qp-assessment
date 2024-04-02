package com.questionpro.grocery.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;


@EnableWebMvc
@Configuration
public class GrocerySecurityConfig {

    //Using default definition of authorities and roles tables
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/groceries").hasRole("USER")
                        .requestMatchers(HttpMethod.GET,"/api/groceries/**").hasRole("USER")
                        .requestMatchers(HttpMethod.PUT,"/api/groceries/buy").hasRole("USER")
                        .requestMatchers(HttpMethod.POST,"/api/groceries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,"/api/groceries").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/groceries/**").hasRole("ADMIN")
                        .requestMatchers(AUTH_WHITELIST).hasRole("ADMIN")
        );
        //Use HTTP basic authentication
        http.httpBasic(Customizer.withDefaults());

        //Disable CSRF. In general, not required for stateless REST APIs
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    private static final String[] AUTH_WHITELIST = {
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/v3/api-docs.yaml",
            "swagger-ui/**",
            "/swagger-ui.html"
    };
}
