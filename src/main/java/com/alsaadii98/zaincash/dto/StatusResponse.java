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
public class StatusResponse {
    private String success;
    private String orderId;
    private String id;
    private long iat;
    private long exp;

    public StatusResponse() {
    }

    public StatusResponse(String success, String orderId, String id, long iat, long exp) {
        this.success = success;
        this.orderId = orderId;
        this.id = id;
        this.iat = iat;
        this.exp = exp;
    }
}
