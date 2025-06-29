package com.shop.shop.JWTConfigurations;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JWTUtils {
    private static final int expirationHours = 5;
    private static final long EXPIRATION = expirationHours *60 * 60 * 1000;
    private static final String SECRET_KEY = "xs-3000wyx";
    private Map<String, Date> invalidatedTokens = new HashMap<>();
    private Map<String, List<String>> userTokens = new HashMap<>(); // To store tokens for each user



    public AuthenticationResponse generateToken(String username, String host, String agent) {
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("tokenHost", host);
        claims.put("userAgent", agent);
        String token = Jwts.builder().setSubject(username).setClaims(claims).setIssuer(username).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRATION)).signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
        return new AuthenticationResponse(token);
    }


    public boolean validateToken(String jwtToken, String host, String agent) {
        Claims claims = getInformationFromToken(jwtToken);
        Date expirationDate = getDateFromTokenClaims(claims);
        String requestAgent = getInfoFromTokenClaims(claims, "userAgent");
        String tokenHost = getInfoFromTokenClaims(claims, "tokenHost");
        return (Objects.equals(agent, requestAgent) && Objects.equals(host, tokenHost) && !expirationDate.before(new Date()));
    }
    public void invalidateToken(String jwtToken) {
        Claims claims = getInformationFromToken(jwtToken);
        Date expirationDate = getDateFromTokenClaims(claims);
        invalidatedTokens.put(jwtToken, expirationDate);
    }
    public void invalidateUserTokens(String username) {
        List<String> tokens = userTokens.get(username);
        if (tokens != null) {
            tokens.forEach(token -> invalidatedTokens.put(token, new Date()));
        }
    }

    public boolean isTokenInvalidated(String jwtToken) {
        Date expirationDate = invalidatedTokens.get(jwtToken);
        return expirationDate != null && !expirationDate.before(new Date());
    }

    public boolean isUserTokenInvalidated(String username, String jwtToken) {
        List<String> tokens = userTokens.get(username);
        return tokens != null && tokens.contains(jwtToken) && isTokenInvalidated(jwtToken);
    }

    private static Date getDateFromTokenClaims(Claims claims) {
        return claims.getExpiration();
    }

    private static String getInfoFromTokenClaims(Claims claims, String key) {
        return claims.get(key).toString();
    }

    private Claims getInformationFromToken(String jwtToken) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(jwtToken).getBody();
    }

    public String getUserFromToken(String token) {
        Claims claims = getInformationFromToken(token);
        return claims.getIssuer();
    }
}
