package com.jwt.auth.model;

public class JWTResponse {
    String token;

    public JWTResponse(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JWTResponse{" +
                "token='" + token + '\'' +
                '}';
    }

    public JWTResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
