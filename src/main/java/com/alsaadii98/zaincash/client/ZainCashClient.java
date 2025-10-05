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

package com.alsaadii98.zaincash.client;

import com.alsaadii98.zaincash.config.ZainCashConfig;
import com.alsaadii98.zaincash.dto.PaymentRequest;
import com.alsaadii98.zaincash.dto.PaymentResponse;
import com.alsaadii98.zaincash.dto.StatusResponse;
import com.alsaadii98.zaincash.util.TokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;


import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class ZainCashClient {

    private final ZainCashConfig config;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public ZainCashClient(ZainCashConfig config) {
        this.config = config;
        this.client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        this.objectMapper = new ObjectMapper();
    }

    public String initiatePayment(PaymentRequest request) {
        String token = generateToken(request);
        String requestBody = buildRequestBody(token);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(config.getBaseUrl() + "/transaction/init"))
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .timeout(Duration.ofSeconds(30))
                .build();

        return executeRequest(httpRequest);
    }

    public StatusResponse checkPaymentStatus(String token) {
        Claims claims = TokenUtil.decodeToken(token, config.getSecret());
        String status = claims.get("status", String.class);
        String orderId = claims.get("orderId", String.class);
        String id = claims.get("id", String.class);

        Date iat = claims.get("iat", Date.class);
        Date exp = claims.get("exp", Date.class);

        return new StatusResponse(
                status, orderId, id, iat, exp
        );
    }

    private String generateToken(PaymentRequest request) {
        long now = Instant.now().toEpochMilli();
        long exp = now + 4 * 60 * 60 * 1000L; // 4 hours

        Map<String, Object> payload = Map.of(
                "amount", request.getAmount(),
                "serviceType", request.getServiceType(),
                "msisdn", config.getMsisdn(),
                "orderId", request.getOrderId(),
                "redirectUrl", request.getRedirectUrl(),
                "iat", now,
                "exp", exp
        );

        return TokenUtil.generateToken(payload, config.getSecret(), exp);
    }

    private String buildRequestBody(String token) {
        Map<String, String> body = Map.of(
                "token", token,
                "merchantId", config.getMerchantId(),
                "lang", config.getLanguage().getCode()
        );

        try {
            return objectMapper.writeValueAsString(body);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize request body", e);
        }
    }

    private String executeRequest(HttpRequest request) {
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new RuntimeException("Payment initiation failed. HTTP " + response.statusCode() + ": " + response.body());
            }

            PaymentResponse paymentResponse = objectMapper.readValue(
                    response.body(), PaymentResponse.class
            );

            return config.getBaseUrl() + "/transaction/pay?id=" + paymentResponse.getId();

        } catch (Exception e) {
            throw new RuntimeException("Payment initiation failed: " + e.getMessage(), e);
        }
    }
}