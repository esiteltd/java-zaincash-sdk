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

package com.alsaadii98.zaincash.config;

import com.alsaadii98.zaincash.enums.Environment;
import com.alsaadii98.zaincash.enums.Language;
import com.alsaadii98.zaincash.exception.InvalidConfigException;
import lombok.Data;

@Data
public class ZainCashConfig {
    // Zain Cash Merchant ID - provided by Zain Cash
    private String msisdn;
    // Zain Cash Secret Key - provided by Zain Cash
    private String secret;
    // Zain Cash Merchant ID - provided by Zain Cash
    private String merchantId;
    // Flag to indicate if the environment is production or sandbox
    // true for production, false for sandbox
    private Environment isProduction;
    // Language preference for the payment interface (e.g., "en" for English, "ar" for Arabic)
    private Language language;

    public ZainCashConfig(String msisdn, String secret, String merchantId, Environment isProduction, Language language) {
        if (msisdn == null || secret == null || merchantId == null || language == null) {
            throw new InvalidConfigException("ZainCash configuration parameters cannot be null");
        }

        this.msisdn = msisdn;
        this.secret = secret;
        this.merchantId = merchantId;
        this.isProduction = isProduction;
        this.language = language;
    }

    public String getBaseUrl() {
        return isProduction.isProduction() ? "https://api.zaincash.iq" : "https://test.zaincash.iq";
    }
}
