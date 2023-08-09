package com.jwt.auth.config;

import com.jwt.auth.helper.JWTUtils;
import com.jwt.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTUtils jwtUtils;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //get JWT
        //Bearer/
        //validate
        String requestHeader = request.getHeader("Authorization");//Bearer 2352345235sdfrsfgsdfsdf
        String username = null;
        String token = null;

        //check null and format1
        if(requestHeader !=null && requestHeader.startsWith("Bearer")) {
            token = requestHeader.substring(7);

            try {
                username = this.jwtUtils.getUsernameFromToken(token);
            } catch (Exception e) {
                e.printStackTrace();
            }

            UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
            //security
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Token is not validated.!!");
            }
        }

            filterChain.doFilter(request,response);
        }

    }

