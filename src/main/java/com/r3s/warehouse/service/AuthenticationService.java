package com.r3s.warehouse.service;

import com.r3s.warehouse.config.AppConstant;
import com.r3s.warehouse.entity.RefreshToken;
import com.r3s.warehouse.entity.User;
import com.r3s.warehouse.model.request.AuthenticationRq;
import com.r3s.warehouse.model.response.AuthenticationRs;
import com.r3s.warehouse.model.request.RegisterRq;
import com.r3s.warehouse.model.response.GeneralRs;
import com.r3s.warehouse.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    public GeneralRs register(RegisterRq inMsg) {

        GeneralRs outMsg = new GeneralRs();
        try {
            User user = new User();
            user.setUsername(inMsg.getUsername());
            user.setPassword(passwordEncoder.encode(inMsg.getPassword()));
            user.setRole(inMsg.getRole());
            userRepository.save(user);

            String accessToken = jwtService.generateToken(user);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
            List<String> roles = user.getRole().getAuthorities()
                    .stream()
                    .map(SimpleGrantedAuthority::getAuthority)
                    .toList();

            outMsg.setMessage("Successfully registered");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outMsg;
    }

    public AuthenticationRs authenticate(AuthenticationRq inMsg) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(inMsg.getUsername(),inMsg.getPassword()));

        User user = userRepository.findByUsername(inMsg.getUsername()).orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        List<String> roles = user.getRole().getAuthorities()
                .stream()
                .map(SimpleGrantedAuthority::getAuthority)
                .toList();
        String accessToken = jwtService.generateToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        AuthenticationRs outMsg = new AuthenticationRs();
        outMsg.setId(user.getId());
        outMsg.setAccessToken(accessToken);
        outMsg.setRefreshToken(refreshToken.getToken());
        outMsg.setRoles(roles);
        outMsg.setTokenType(AppConstant.TokenType.BEARER.name());
        return outMsg;
    }
}
