package com.bitsvalley.restclient.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class TestController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api/organizations")
    public ResponseEntity<?> getOrganizations() {
        String url = "https://erestaupos.kapava.com/restaurantsolutions/public/api/organizations";
        url = "https://sandbox.momodeveloper.mtn.com/collection/token/";

        final String apiUser = "a24fa60e-4238-445b-81c0-b266972e6df3";
        final String apiKey = "4793e92622d44799860d68afd3b62152";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        requestHeaders.add("Authorization", "Basic " + apiUser + ":" + apiKey);
        requestHeaders.add("Ocp-Apim-Subscription-Key", "df3ded9380234a1d9d62dbf2ecb78cd3");


        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);


        System.out.println("Printing Response");
        System.out.println(responseEntity.getBody());
        return ResponseEntity.ok(responseEntity.getBody());
    }
}
