package com.r3s.warehouse.service;

import com.r3s.warehouse.config.AppProperties;
import com.r3s.warehouse.entity.RefreshToken;
import com.r3s.warehouse.exception.BadRequestException;
import com.r3s.warehouse.model.request.RefreshTokenRq;
import com.r3s.warehouse.repository.UserRepository;
import com.r3s.warehouse.config.AppConstant;
import com.r3s.warehouse.entity.User;
import com.r3s.warehouse.model.response.RefreshTokenRs;
import com.r3s.warehouse.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Base64;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;
    private final AppProperties appProperties;

    public RefreshToken createRefreshToken(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setRevoked(false);
        refreshToken.setToken(Base64.getEncoder().encodeToString(UUID.randomUUID().toString().getBytes()));
        refreshToken.setExpiresAt(Instant.now().plusMillis(appProperties.getRefreshExpiration()));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verifyExpiration(RefreshToken refreshToken) {
        if (refreshToken == null) {
            log.error("Refresh Token is Null");
            throw new BadRequestException("Refresh Token is Null");
        }
        if (refreshToken.getExpiresAt().compareTo(Instant.now()) < 0) {
            Optional<RefreshToken> revoked = refreshTokenRepository.findByToken(refreshToken.getToken());
            revoked.get().setRevoked(true);
            refreshTokenRepository.save(revoked.get());
//                    refreshTokenRepository.delete(refreshToken);
            throw new BadRequestException("Refresh Token is Expired");
        }
        return refreshToken;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshTokenRs generateNewToken(RefreshTokenRq refreshTokenRq) {
        try {

        }catch (Exception e) {
            e.printStackTrace();

        }
        User user = refreshTokenRepository.findByToken(refreshTokenRq.getRefreshToken())
                .map(this::verifyExpiration)
                .map(RefreshToken::getUser)
                .orElseThrow(() -> new BadRequestException( "Refresh token does not exist"));

        String accessToken = jwtService.generateToken(user);
        RefreshTokenRs outMsg = new RefreshTokenRs();
        outMsg.setAccessToken(accessToken);
        outMsg.setRefreshToken(refreshTokenRq.getRefreshToken());
        outMsg.setTokenType(AppConstant.TokenType.BEARER.name());
        return outMsg;
    }

    public void deleteByToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(refreshTokenRepository::delete);
    }
}
