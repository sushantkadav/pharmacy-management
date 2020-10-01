package com.fluxinfotech.pharmacymanagementsystem.securityConfig;

import com.fluxinfotech.pharmacymanagementsystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthUserDetailsService authUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/token")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
            throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                            authenticationRequest.getPassword())
            );
        } catch (BadCredentialsException badEx) {
            badEx.printStackTrace();
            throw new Exception("Incorrect username or password", badEx);
        }
//        final UserDetails userDetails = authUserDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());

//        final String jwt = jwtUtil.generateToken(userDetails);
        //---- OR -----
        final String jwt = jwtUtil.generateToken(authenticationRequest.getUsername());
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @GetMapping
    public String getString() {
        return "sanity check for auth-server";
    }


}



           /* Movie movie = webClient.build()
                    .get()
                    .uri("http://movie-info-service/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();*/