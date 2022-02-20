package com.bitsvalley.restclient.controllers;
import java.io.*;

import com.bitsvalley.restclient.Payer;
import com.bitsvalley.restclient.PaymentBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import okhttp3.*;
import java.util.Base64;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PayController
{

    @GetMapping("/pay")
    public ResponseEntity<?> makeApiCall(){

        final String url = "https://sandbox.momodeveloper.mtn.com/collection/v1_0/requesttopay";
        final String uuid = UUID.randomUUID().toString();

        final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSMjU2In0.eyJjbGllbnRJZCI6ImEyNGZhNjBlLTQyMzgtNDQ1Yi04MWMwLWIyNjY5NzJlNmRmMyIsImV4cGlyZXMiOiIyMDIyLTAyLTIwVDE0OjQ1OjI5LjMxMCIsInNlc3Npb25JZCI6ImZmMjYxNTU0LTA4MzktNGQ3Ni05YzA5LTQwNzdlZGQ5NDNhMyJ9.iYtXXutfgu4-0K2PomKDucSchmGY0OlpZ6c9hh5O-KjkxAcElD5XExh-jHZfhiOYydh2dwCam5kffGLCoemzH7Zmcud98JFc9O7cBeSoVoYDInSTFXwllv9lizVxikiskPPEG5INAqZfWlMzkprUHK0u_FVbAmdaHQv8AgouurMxcm8YBtoWuTdYgV33rNL6HvkELCk2T3JhNrV65B7fxzJpQT8rmcQPr6q9aXkdvtEyIxPNsTcno2blYyFsrrUyi_UpD3UkVecf43-VYk_0YQspsmRrQOQ0V_Fc9TkZxgKpg8FFXmI5s9t17B7cNovXtsN1sFyu8vdwFrkVMoys9g";

        String jsonBody = "";

        try {
            jsonBody = this.getJson(uuid, "673901939");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(jsonBody);


        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, jsonBody);



        Request request = new Request.Builder()
                .url(url)
                .method("POST", body)
                .addHeader("Ocp-Apim-Subscription-Key", "df3ded9380234a1d9d62dbf2ecb78cd3")
                .addHeader("X-Target-Environment", "sandbox")
                .addHeader("X-Reference-Id", uuid)
                .addHeader("Authorization", "Bearer " + token)
                .build();

        try
        {
            Response response = client.newCall(request).execute();
            System.out.println(response.body().string());

            if(response.code() >= 200 && response.code() <= 205)
            {
                System.out.println(response.code() + " Request successful");
                final String responseBody = response.body().string();

                System.out.println(responseBody);
            }
            else {
                System.out.println("Error");
                System.out.printf("%d Message: %s", response.code(), response.message());
            }

            System.out.println("");
            System.out.println("Request Finished");
        }
        catch (IOException e)
        {
            System.out.println("Error encountered");
            e.printStackTrace();
        }

        return ResponseEntity.ok("Request to Pay");
    }

    private String getJson(String uuid, String tel) throws JsonProcessingException {

//        return "{\n  \"amount\": \"1000\",\n  \"currency\": \"EUR\",\n  \"externalId\": \"1236985477\",\n  \"payer\": {\n    \"partyIdType\": \"MSISDN\",\n    \"partyId\": \"673901939\"\n  },\n  \"payerMessage\": \"Please pay for your service\",\n  \"payeeNote\": \"\"\n}";
        PaymentBody paymentBody = new PaymentBody();
        paymentBody.setAmount("1000");
        paymentBody.setCurrency("EUR");
        paymentBody.setPayeeNote("POS consumption");
        paymentBody.setExternalId(uuid);

        paymentBody.setPayer(new Payer(tel));

        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(paymentBody);
        return s;
    }

}
