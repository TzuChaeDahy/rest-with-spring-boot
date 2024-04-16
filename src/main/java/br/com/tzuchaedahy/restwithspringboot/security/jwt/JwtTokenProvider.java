package br.com.tzuchaedahy.restwithspringboot.security.jwt;

import br.com.tzuchaedahy.restwithspringboot.exceptions.InvalidJwtAuthenticationException;
import br.com.tzuchaedahy.restwithspringboot.person.controller.dto.response.TokenDTO;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey = "secret";

    @Value("${security.jwt.token.expire-length}")
    private long expireLength = 3600000;

    @Autowired
    UserDetailsService userDetailsService;

    Algorithm algorithm = null;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        algorithm = Algorithm.HMAC256(secretKey.getBytes());
    }

    public TokenDTO createTokenDTO(String userName, List<String> roles) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expireLength);

        String accessToken = getAccessToken(userName, roles, now, expiryDate);
        String refreshToken = getRefreshToken(userName, roles, now);

        return new TokenDTO(
                userName,
                true,
                now,
                expiryDate,
                accessToken,
                refreshToken
        );
    }

    public TokenDTO refreshToken(String refreshToken) {
        if (refreshToken.contains("Bearer ")) {
            refreshToken = refreshToken.substring("Bearer ".length());
        }

        DecodedJWT decodedJWT = decodeJWT(refreshToken);
        String userName = decodedJWT.getSubject();
        List<String> roles = decodedJWT.getClaim("roles").asList(String.class);

        return createTokenDTO(userName, roles);
    }

    private String getRefreshToken(String userName, List<String> roles, Date now) {
        Date newExpireDate = new Date(now.getTime() + (expireLength * 3));

        return JWT.
                create().
                withClaim("roles", roles).
                withSubject(userName).
                withIssuedAt(now).
                withExpiresAt(newExpireDate).
                sign(algorithm).
                strip();
    }

    private String getAccessToken(String userName, List<String> roles, Date now, Date expiryDate) {
        String issuerURL = ServletUriComponentsBuilder.
                fromCurrentContextPath().
                build().
                toUriString();

        return JWT.
                create().
                withClaim("roles", roles).
                withSubject(userName).
                withIssuedAt(now).
                withExpiresAt(expiryDate).
                withIssuer(issuerURL).
                sign(algorithm).
                strip();
    }

    public Authentication getAuthentication(String token) {
        DecodedJWT decodedJWT = decodeJWT(token);

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(decodedJWT.getSubject());

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private DecodedJWT decodeJWT(String token) {
        JWTVerifier verifier = JWT.require(algorithm).build();

        return verifier.verify(token);
    }

    public String resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        return authHeader.substring("Bearer ".length());
    }

    public boolean validateToken(String token) {
        DecodedJWT decodedJWT = decodeJWT(token);

        try {
            return !decodedJWT.getExpiresAt().before(new Date());
        } catch (Exception exception) {
            throw new InvalidJwtAuthenticationException("expired or invalid jwt token");
        }
    }
}
