package com.alura.conversormonedas.service;

import com.alura.conversormonedas.model.ConsultarMonedaAPI;
import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultarMonedaService {
    public ConsultarMonedaAPI getMoneda(String moneda){
        URI url = URI.create("https://v6.exchangerate-api.com/v6/b37337a6bb347bda03da84c9/latest/" + moneda.toLowerCase().replace(" ", ""));

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(url).build();
        HttpResponse<String> response;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            throw new RuntimeException("Moneda no encontrada");
        }

        return new Gson().fromJson(response.body(), ConsultarMonedaAPI.class);
    }
}
