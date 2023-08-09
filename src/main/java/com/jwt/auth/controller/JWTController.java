package com.jwt.auth.controller;

import com.jwt.auth.helper.JWTUtils;
import com.jwt.auth.model.JWTRequest;
import com.jwt.auth.model.JWTResponse;
import com.jwt.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JWTController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest) throws Exception {
        System.out.println(jwtRequest);

        try{

            this.authenticationManager.authenticate
                    (new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(),
                            jwtRequest.getPassword()));
//            System.out.println("Password: "+jwtRequest.getPassword());

        }catch (UsernameNotFoundException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials !!");
        }catch (BadCredentialsException e){
            e.printStackTrace();
            throw new Exception("Bad Credentials !!");
        }
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        System.out.println("JWT Token: "+token);
//        System.out.println("Password: "+jwtRequest.getPassword());

        return ResponseEntity.ok(new JWTResponse(token));
    }
}
