package com.holsync.security.SpringSecDemo.securities;/*
 * Author: Sachin
 * Date: 22-Mar-25
 * Description:
 */

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticateFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        //get Auth header
        final String authHeader = request.getHeader("Authorization");

        //Check header and Bearer is present
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }

        // Extract Token
        final String token = authHeader.substring(7); // Remove "Bearer "

        // Extract Email (Username) from Token
        String email = jwtService.extractUsername(token);

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
            //Fetch User From Db
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

            if(jwtService.validateToken(token,userDetails.getUsername())){

                //Iska kaam hai user ko authenticated mark karna! ✅
                UsernamePasswordAuthenticationToken authToken=
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                //Yeh line user ki IP Address, Browser Info, Session Details attach karti hai
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // ✅ Set Authentication in Security Context
                //Ab Spring Security ko pata hai ki yeh request authenticated hai! 🔥
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
