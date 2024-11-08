package com.example.springsec.config;/*
 * Author: Your Name
 * Date: 06-Nov-24
 * Time: 3:48 PM
 */

import com.example.springsec.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    public WebSecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    //Step 1: Define the filterChain Bean
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
      return  httpSecurity
              .csrf(c -> c.disable())
              .authorizeHttpRequests(
                      authorizationManagerRequestMatcherRegistry ->
                              authorizationManagerRequestMatcherRegistry
                                      .requestMatchers("api/vedant/register","api/vedant/login").permitAll()
                                      .anyRequest().authenticated())// Allow login/signup pages for everyone)
              .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
              .httpBasic(Customizer.withDefaults()) //Optionally enable basic HTTP authentication (for APIs)
              .build();
    }

    /*
         * User.withUsername("user"): Creates a user with the username "user".
         * password("{noop}password"):Sets the password to "password".
             The {noop} means the password is not encoded (plaintext password).
             In a real-world application, you would use password encoding (e.g., BCrypt).
         * roles("USER"): Assigns the role "USER" to the user.
         * InMemoryUserDetailsManager: This is a simple implementation of UserDetailsService that stores user information in memory.

    * */
    // Bean that provides UserDetailsService for Spring Security

    public UserDetailsService userDetailsService(){
        // Create a hardcoded user with username, password, and roles
        UserDetails sachin = User.withUsername("sachin")
                .password("{noop}sony")  // {noop} means no password encoding (plaintext password)
                .roles("USER")
                .build();

        UserDetails sony = User.withUsername("sony")
                .password("{noop}sachin")  // {noop} means no password encoding (plaintext password)
                .roles("USER")
                .build();

        List<UserDetails> listOfUsers = List.of(sachin,sony);
        // Create an InMemoryUserDetailsManager to manage the users in memory
        return new InMemoryUserDetailsManager(listOfUsers);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }
}
