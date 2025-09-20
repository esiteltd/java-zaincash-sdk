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

package com.alsaadii98.zaincash.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaymentResponse {
    private String source;
    private String type;
    private String amount;
    private String to;
    private String serviceType;
    private String lang;
    private String orderId;
    private List<?> currencyConversion;
    private String referenceNumber;
    private String redirectUrl;
    private boolean credit;
    private String status;
    private boolean reversed;
    private String createdAt;
    private String updatedAt;
    private String id;

    public PaymentResponse() {
    }

    public PaymentResponse(String source, String type, String amount, String to, String serviceType, String lang, String orderId, List<?> currencyConversion, String referenceNumber, String redirectUrl, boolean credit, String status, boolean reversed, String createdAt, String updatedAt, String id) {
        this.source = source;
        this.type = type;
        this.amount = amount;
        this.to = to;
        this.serviceType = serviceType;
        this.lang = lang;
        this.orderId = orderId;
        this.currencyConversion = currencyConversion;
        this.referenceNumber = referenceNumber;
        this.redirectUrl = redirectUrl;
        this.credit = credit;
        this.status = status;
        this.reversed = reversed;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.id = id;
    }
}