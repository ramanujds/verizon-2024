package com.rds.authenticationservice.util;

import com.rds.authenticationservice.service.UserDetailsServiceImpl;
import com.rds.authenticationservice.service.UserInfoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter{

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info("Filter intercepted");
        String authHeader = request.getHeader("Authorization");
        String token= null;
        String userName = null;
        if(authHeader !=null && authHeader.startsWith("Bearer")){

            token = authHeader.substring(7);
            log.info("Token: "+token);
            userName = jwtUtil.getUsernameFromToken(token);
            log.info("Username from token: "+userName);
        }
        if(userName !=null && SecurityContextHolder.getContext().getAuthentication()==null){
            log.info("Token is getting validated");
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            if(jwtUtil.validateToken(token,userDetails)){
                log.info("Token is validated");
                UsernamePasswordAuthenticationToken authToken =  new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        log.info("Filter is passing the request");
        filterChain.doFilter(request,response);
    }
}

