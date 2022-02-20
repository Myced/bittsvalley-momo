package com.bitsvalley.restclient.controllers;

import java.io.*;
import okhttp3.*;
import java.util.Base64;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;


@Controller
public class ApiController {

    private RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/api")
    public ResponseEntity<?> makeApiCall(){
        final String url = "https://sandbox.momodeveloper.mtn.com/collection/token/";
        final String apiUser = "a24fa60e-4238-445b-81c0-b266972e6df3";
        final String apiKey = "4793e92622d44799860d68afd3b62152";

        final String authString = apiUser + ":" + apiKey;

        final String authorization = new String(Base64.getEncoder().encode(authString.getBytes()));


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Ocp-Apim-Subscription-Key", "df3ded9380234a1d9d62dbf2ecb78cd3")
                .addHeader("Authorization", "Basic " + authorization)
                .build();

        try
        {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());
        }
        catch (IOException e)
        {
            System.out.println("Error encountered");
            e.printStackTrace();
        }

        return ResponseEntity.ok("Api called");
    }
}
