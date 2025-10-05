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

import java.util.Date;

public class StatusResponse {
    private String success;
    private String orderId;
    private String id;
    private Date iat;
    private Date exp;

    public StatusResponse() {
    }

    public StatusResponse(String success, String orderId, String id, Date iat, Date exp) {
        this.success = success;
        this.orderId = orderId;
        this.id = id;
        this.iat = iat;
        this.exp = exp;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Object getIat() {
        return iat;
    }

    public void setIat(Date iat) {
        this.iat = iat;
    }

    public Object getExp() {
        return exp;
    }

    public void setExp(Date exp) {
        this.exp = exp;
    }
}
