package com.electroniccommunication.echat.config;

import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.security.auth.kerberos.EncryptionKey;
import java.util.Date;

@Component
public class JWTGenerator {
    @Value("${echat.jwtSecretKey}")
    private String secretKey;
    @Value("${echat.jwtExpirationTime}")
    private String jwtExpirationTime;

    private SecretKey key;
    private JwtParser jwtParser;

    @PostConstruct
    // injection of secretKey and jwtExpirationTime happens after constructor
    void init() {
        // https://techcommunity.microsoft.com/blog/coreinfrastructureandsecurityblog/decrypting-the-selection-of-supported-kerberos-encryption-types/1628797
        key = new EncryptionKey(secretKey.getBytes(), 31);
        jwtParser = Jwts.parser().decryptWith(key).build();
    }

    public String generateToken(Authentication authentication) {
        String username = authentication.getName();

        Date expiryDate = new Date(new Date().getTime() + jwtExpirationTime);
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(expiryDate)
                .signWith(key)
                .compact();
    }

    public String getUserNameFromJWT(String token) {
        return jwtParser.parseSignedClaims(token).getPayload().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            jwtParser.parseSignedClaims(token);
            return true;
        } catch (Exception exception){
            throw new AuthenticationCredentialsNotFoundException("something went wrong with token parsing");
        }
    }
}
