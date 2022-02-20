package com.bitsvalley.restclient.controllers;

import okhttp3.*;
import okhttp3.MediaType;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;

@Controller
public class NewController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/momo")
    public ResponseEntity<?> getOrganizations() {
/**
//        String url = "https://erestaupos.kapava.com/restaurantsolutions/public/api/organizations";

        //get the collection auth token.
        String tokenEndpoint = "https://sandbox.momodeveloper.mtn.com/collection/token";

        String apiUser = "a24fa60e-4238-445b-81c0-b266972e6df3";
        String apiKey = "4793e92622d44799860d68afd3b62152";

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type", "application/json");
        requestHeaders.add("Ocp-Apim-Subscription-Key", "df3ded9380234a1d9d62dbf2ecb78cd3");
//        requestHeaders.add("Authorization", "Basic " + apiUser + ":" + apiKey);
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(tokenEndpoint, HttpMethod.POST, requestEntity, String.class);
        if(responseEntity.getStatusCodeValue()==302){
            URI location = responseEntity.getHeaders().getLocation();
            responseEntity = restTemplate.exchange(location.toString(), HttpMethod.POST, requestEntity, String.class);

        }

 **/


        System.out.println("Making HTTP request to Momo API");

        //setup variables.
        final String tokenEndpoint = "https://sandbox.momodeveloper.mtn.com/collection/token";

        final String apiUser = "a24fa60e-4238-445b-81c0-b266972e6df3";
        final String apiKey = "4793e92622d44799860d68afd3b62152";


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "");

        Request request = new Request.Builder()
                .url(tokenEndpoint)
                .method("POST", body)
                .addHeader("Ocp-Apim-Subscription-Key", "df3ded9380234a1d9d62dbf2ecb78cd3")
                .addHeader("Authorization", "Basic YTI0ZmE2MGUtNDIzOC00NDViLTgxYzAtYjI2Njk3MmU2ZGYzOjQ3OTNlOTI2MjJkNDQ3OTk4NjBkNjhhZmQzYjYyMTUy")
                .build();

        try {
            Response response = client.newCall(request).execute();

            System.out.println(response.body());
        } catch (IOException e) {
            final String errorMessage = e.getMessage();

            System.out.println("Error Message below");
            System.out.println(errorMessage);
        }

        return ResponseEntity.ok("data");

    }

}