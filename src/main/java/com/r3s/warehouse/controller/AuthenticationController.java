package com.r3s.warehouse.controller;


import com.r3s.warehouse.model.request.AuthenticationRq;
import com.r3s.warehouse.model.request.RefreshTokenRq;
import com.r3s.warehouse.model.request.RegisterRq;
import com.r3s.warehouse.service.AuthenticationService;
import com.r3s.warehouse.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;

    @Operation(
            summary = "Registration Account")
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRq inMsg) {
        Object request = authenticationService.register(inMsg);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini untuk mendapatkan access token",
            summary = "Authentication Account")
    @PostMapping("/authentication")
    public ResponseEntity<?> authentication(@Valid @RequestBody AuthenticationRq inMsg) {
        Object request = authenticationService.authenticate(inMsg);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @Operation(description = "Endpoint ini untuk mendapatkan access token dengan menggunakan refresh token",
            summary = "Generate New Token")
    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRq inMsg) {
        Object request = refreshTokenService.generateNewToken(inMsg);
        return new ResponseEntity<>(request, HttpStatus.OK);
    }

    @Operation(
            summary = "Info Account")
    @GetMapping("/info")
    public ResponseEntity<?> getAuthentication(@RequestParam String username,
                                               @RequestParam String password) {
        return new ResponseEntity<>(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password)), HttpStatus.OK);
    }
}
