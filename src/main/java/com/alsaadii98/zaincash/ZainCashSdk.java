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

package com.alsaadii98.zaincash;

import com.alsaadii98.zaincash.client.ZainCashClient;
import com.alsaadii98.zaincash.config.ZainCashConfig;
import com.alsaadii98.zaincash.dto.PaymentRequest;
import com.alsaadii98.zaincash.dto.StatusResponse;

public class ZainCashSdk {
    private final ZainCashClient client;

    public ZainCashSdk(ZainCashConfig config) {
        this.client = new ZainCashClient(config);
    }

    /**
     * Initiate a payment and return the payment URL.
     */
    public String initiatePayment(PaymentRequest paymentRequest) {
        return client.initiatePayment(paymentRequest);
    }

    /**
     * Check payment status from the redirect token.
     */
    public StatusResponse checkPaymentStatus(String token) {
        return client.checkPaymentStatus(token);
    }

    public ZainCashClient client() {
        return client;
    }
}
