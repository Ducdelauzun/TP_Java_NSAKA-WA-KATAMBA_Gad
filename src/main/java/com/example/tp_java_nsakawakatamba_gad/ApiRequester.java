package com.example.tp_java_nsakawakatamba_gad;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiRequester {

    private static final String API_URL = "https://api.aviationstack.com/v1/flights";
    private static final String API_KEY = "2c462a6875758ab646835ceb6cb05b0f";

    public String getFlightData() {
        HttpClient client = HttpClient.newHttpClient();

        String urlWithApiKey = API_URL + "?access_key=" + API_KEY;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlWithApiKey))
                .header("Content-Type", "application/json")
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return response.body();
            } else {
                System.out.println("Erreur lors de l'appel API: " + response.statusCode());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
