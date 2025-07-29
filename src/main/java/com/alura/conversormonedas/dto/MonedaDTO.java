package com.alura.conversormonedas.dto;

import com.alura.conversormonedas.model.ConsultarMonedaAPI;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class MonedaDTO {
    @SerializedName("result")
    public boolean success;

    @SerializedName("baseCode")
    public String currency;

    @SerializedName("conversionRates")
    public Map<String, Double> conversions;

    public MonedaDTO (ConsultarMonedaAPI consultarMonedaAPI){
        if(consultarMonedaAPI.result().contains("error")){
            throw new RuntimeException("Moneda no encontrada");
        }
        this.success = true;
        this.currency = consultarMonedaAPI.base_code();
        this.conversions = consultarMonedaAPI.conversion_rates();
    }

    public double getConversionRate(String currency){
        if (!conversions.containsKey(currency)){
            throw new IllegalArgumentException("Moneda no válida");
        }
        return conversions.get(currency);
    }

    public String getCurrency(){
        return currency;
    }

    @Override
    public String toString(){
        return "Moneda: " + currency;
    }

}
