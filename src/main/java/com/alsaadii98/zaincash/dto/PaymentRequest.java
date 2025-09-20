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

@Data
public class PaymentRequest {
    // The total amount for the payment of your order in IQD (Iraqi Dinar).
    // In case the currency is Dollar (USD), the amount should be multiplied by exchange rate
    // (e.g., if the amount is 10 USD and the exchange rate is 1450 IQD, then the amount should be 14500).
    // Must be more than 1000 IQD.
    private Integer amount;
    // A brief description of the service (e.g., "Book Purchase", "Internet Subscription", etc.).
    private String serviceType;
    // Help you in tagging transactions with your internal system (e.g., "Order12345", "Subscription67890", etc.).
    // Type is String with max length of 512 characters.
    private String orderId;
    // The URL to which the user will be redirected after completing the payment process.
    // This URL should be a valid endpoint in your system that can handle the response from ZainCash.
    // Ensure that this URL is accessible and can process the payment status.
    private String redirectUrl;

    public PaymentRequest() {
    }

    public PaymentRequest(Integer amount, String serviceType, String orderId, String redirectUrl) {
        this.amount = amount;
        this.serviceType = serviceType;
        this.orderId = orderId;
        this.redirectUrl = redirectUrl;
    }
}
