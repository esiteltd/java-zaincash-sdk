/*
 * Copyright (c) 2025 Ali Hasan Abdulhadi (https://github.com/alsaadii98). All rights reserved.
 *
 * This file is part of the eSITE LTD SDK.
 *
 * You are free to use, modify, and distribute this code,
 * provided that this notice is retained in all copies or
 * substantial portions of the Software.
 *
 * This SDK integrates with the ZainCash API.
 *
 * Author: Ali Hasan Abdulhadi
 * GitHub: https://github.com/alsaadii98
 */

package com.alsaadii98.zaincash.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class TokenUtil {
    public static String generateToken(Map<String, Object> claims, String secret, long expiration) {
        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(expiration))
                .signWith(getSigningKey(secret))
                .compact();
    }

    public static Claims decodeToken(String token, String secret) {
        return Jwts.parser()
                .verifyWith(getSigningKey(secret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static SecretKey getSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
