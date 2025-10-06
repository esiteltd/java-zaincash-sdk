<img src="./assets/ZainCash-White.svg" alt="Zain Cash Logo" width="200"/>

# Zain Cash Payment Gateway - Java SDK

[![GitHub Package](https://img.shields.io/badge/GitHub-Package-blue.svg)](https://github.com/esiteltd/java-zaincash-sdk/packages)
[![GitHub Release](https://img.shields.io/github/v/release/esiteltd/java-zaincash-sdk)](https://github.com/esiteltd/java-zaincash-sdk/releases)
[![Java Version](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://openjdk.org/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

---

## Getting Started

### 1. Add the Dependency

Add to your `build.gradle`:

```groovy
dependencies {
    implementation 'com.esite.zaincash:zaincash-sdk:0.0.1'
}
```

---

### 2. Configure Application Properties

Add to your `application.properties` or `.yml`:

```properties
zaincash:
msisdn:
secret: 
merchant-id:
environment: 
language: 
```

- **Environment:** `PRODUCTION` or `SANDBOX`
- **Language:** `ENGLISH` or `ARABIC`

---

## Creating a Payment Request

Use the `PaymentRequest` class:

```java
PaymentRequest paymentRequest = new PaymentRequest(
        your_amount,
        your_service_type,
        your_order_id,
        "https://your-redirect-url.com"
);
```

**Notes:**

- **Amount (IQD):**
    - Must be in Iraqi Dinar (IQD).
    - If using USD, multiply by the exchange rate (e.g., `10 USD * 1450 = 14500 IQD`).
    - Must be greater than **1000 IQD**.

- **Service Type:**  
  Brief description (e.g., `"Book Purchase"`, `"Internet Subscription"`).

- **Order ID:**  
  Used for internal tracking (e.g., `"Order12345"`).
    - Type: String
    - Max Length: 512 characters

- **Redirect URL:**  
  Publicly accessible endpoint to handle ZainCash response.

---

## Initiate Payment

Inject `ZainCashSDK` and use:

```java
private final ZainCashSDK zainCashSDK;

public void initializePayment() {
    PaymentRequest paymentRequest = new PaymentRequest(
            your_amount,
            your_service_type,
            your_order_id,
            "https://your-redirect-url.com"
    );

    try {
        // Redirect user to payment URL
        String paymentResponse = zainCashSDK.initiatePayment(paymentRequest);
    } catch (ZainCashException e) {
        // Handle exception
    }
}
```

---

## Checking Payment Status

Extract the `token` from the redirect URL and check status:

```java
private final ZainCashSDK zainCashSDK;

public void checkPaymentStatus(String token) {
    try {
        StatusResponse paymentStatusResponse = zainCashSDK.checkPaymentStatus(token);
    } catch (ZainCashException e) {
        // Handle exception
    }
}
```

---

## StatusResponse Class

```java
public class StatusResponse {
    private String success;
    private String orderId;
    private String id;
    private Date iat;
    private Date exp;
}
```

---

## Support

For any issues, feel free to reach out.

Developed with ❤️ by [@alsaadii98](https://github.com/alsaadii98)
at [eSITE Information Technology](https://esite-iq.com).