package com.fluxinfotech.pharmacymanagementsystem.securityConfig;

public class AuthenticationResponse {
    private final String jwt;



    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
