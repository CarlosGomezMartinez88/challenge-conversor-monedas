package com.alura.conversormonedas.model;

import java.util.Map;

public record ConsultarMonedaAPI(String result,
                                 String base_code,
                                 Map<String, Double> conversion_rates) {

}
