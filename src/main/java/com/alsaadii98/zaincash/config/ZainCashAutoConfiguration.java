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

import com.alsaadii98.zaincash.ZainCashSdk;
import com.alsaadii98.zaincash.client.ZainCashClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(ZainCashSdk.class)
@EnableConfigurationProperties(ZainCashProperties.class)
public class ZainCashAutoConfiguration {

    private final ZainCashProperties zainCashProperties;


    public ZainCashAutoConfiguration(ZainCashProperties zainCashProperties) {
        this.zainCashProperties = zainCashProperties;
    }

    @Bean
    @ConditionalOnMissingBean
    public ZainCashConfig zainCashConfig() {
        return new ZainCashConfig(
                zainCashProperties.getMsisdn(),
                zainCashProperties.getSecret(),
                zainCashProperties.getMerchantId(),
                zainCashProperties.getEnvironment(),
                zainCashProperties.getLanguage()
        );
    }

    @Bean
    @ConditionalOnMissingBean
    public ZainCashClient zainCashClient(ZainCashConfig config) {
        return new ZainCashClient(config);
    }

    @Bean
    @ConditionalOnMissingBean
    public ZainCashSdk zainCashSdk(ZainCashConfig config) {
        return new ZainCashSdk(config);
    }
}
