package com.fogalde.currency_converter.utils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeApiRequest {
    private String baseUrl;
    private double amount;
    private String baseCode;
    private String targetCode;
    private String endpoint;

    public ExchangeApiRequest(double amount, String baseCode, String targetCode) {
        this.baseUrl = "https://v6.exchangerate-api.com/v6/18c51f914e32984e0cb912a0/pair/";
        this.amount = amount;
        this.baseCode = baseCode;
        this.targetCode = targetCode;
        this.endpoint = this.baseUrl+baseCode+"/"+targetCode+"/"+amount;
    }

    public String getExchangeApiConversion() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(this.endpoint))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
