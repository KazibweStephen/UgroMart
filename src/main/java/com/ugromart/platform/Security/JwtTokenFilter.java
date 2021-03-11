package com.ugromart.platform.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.ObjectUtils.isEmpty;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private  JwtTokenUtil jwtTokenUtil;
    @Autowired
    private  UserDetailsService userDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        //get authorization header and validate
        final  String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        if(isEmpty(header)|| !header.startsWith("Bearer")){
            chain.doFilter(request,response);
            return;
        }
        //get jwttoken and validate
        final String token =header.split(" ")[1].trim();
        if(!jwtTokenUtil.validate(token)){
            chain.doFilter(request,response);
            return;
        }
        //get user identity and set it on the spring context
        UserDetails userDetails =userDetailsService
                .loadUserByUsername(jwtTokenUtil.getUsername(token));

        UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails==null? new ArrayList<>() :userDetails.getAuthorities()
        );
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request,response);
    }
}
