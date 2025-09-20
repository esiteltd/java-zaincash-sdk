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
import okhttp3.*;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;

public class ZainCashClient {

    private static final MediaType JSON = MediaType.get("application/json");

    private final ZainCashConfig config;
    private final OkHttpClient client;
    private final ObjectMapper objectMapper;

    public ZainCashClient(ZainCashConfig config) {
        this.config = config;
        this.client = new OkHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public String initiatePayment(PaymentRequest request) {
        String token = generateToken(request);
        String requestBody = buildRequestBody(token);

        Request httpRequest = new Request.Builder()
                .url(config.getBaseUrl() + "/transaction/init")
                .post(RequestBody.create(requestBody, JSON))
                .build();

        return executeRequest(httpRequest);
    }

    // Token Will be extract from Request Query Parameter
    public StatusResponse checkPaymentStatus(String token) {
        Claims claims = TokenUtil.decodeToken(token, config.getSecret());
        String status = claims.get("status", String.class);
        String orderId = claims.get("orderId", String.class);
        String id = claims.get("id", String.class);
        long iat = claims.get("iat", Long.class);
        long exp = claims.get("exp", Long.class);

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

    private String executeRequest(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful() || response.body() == null) {
                throw new RuntimeException("Payment initiation failed: " + response);
            }

            PaymentResponse paymentResponse = objectMapper.readValue(
                    response.body().string(), PaymentResponse.class
            );

            return config.getBaseUrl() + "/transaction/pay?id=" + paymentResponse.getId();

        } catch (IOException e) {
            throw new RuntimeException("Payment initiation failed", e);
        }
    }
}
