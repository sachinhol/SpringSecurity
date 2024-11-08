package com.example.springsec.config;/*
 * Author: Your Name
 * Date: 07-Nov-24
 * Time: This filter will intercept requests, check if a JWT token is present, validate it, and set the authentication in the security context.
 */

import com.example.springsec.model.DoctorDetails;
import com.example.springsec.service.CustomUserDetailsService;
import com.example.springsec.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    //The core method that intercept each request and checks for valid Jwt token
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Step  1 :  Get the "Authorization" header from the request
        String authorizationHeader = request.getHeader("Authorization");

        //Step 2 : Check "Authorization" header contains token with prefix Bearer
        if(authorizationHeader !=null && authorizationHeader.startsWith("Bearer ")){

            //Extract Token from the Bearer part
            String jwt =  authorizationHeader.substring(7);

            //Step 3 : Extract the userName from the token
            String userName = jwtUtil.extractUserName(jwt);

            //Step 4 :  Check if user in not already authenticated in the request
            if(StringUtils.isNotBlank(userName) && SecurityContextHolder.getContext().getAuthentication() == null){
                //load the user details (like roles and permission) using CustomUserDetailsService
                UserDetails userDetails = userDetailsService.loadUserByUsername(userName);

                //Step 5 : Validate the token to check It's legitimate and not expire
                if(jwtUtil.isTokenValid(jwt,userDetails)){
                  //if valid create an authentication object with the userDetails
                    UsernamePasswordAuthenticationToken authentication
                            = new UsernamePasswordAuthenticationToken(userDetails,null, userDetails.getAuthorities());

                    //Attach additional details of the request, basically as session
                    authentication.setDetails(
                            new WebAuthenticationDetailsSource()
                                    .buildDetails(request)
                    );

                    //Step 6 : Set the authentication object in SecurityContextHolder
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        //Step 7 : Continue with the request processing
        //if there is no JWT or if its invalid, this step simply let the request proceed without setting authentication
        filterChain.doFilter(request,response);
    }
}
