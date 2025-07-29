package com.alura.conversormonedas;

import com.alura.conversormonedas.converter.ConversorMoneda;
import com.alura.conversormonedas.dto.MonedaDTO;
import com.alura.conversormonedas.model.ConsultarMonedaAPI;
import com.alura.conversormonedas.service.ConsultarMonedaService;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        ConsultarMonedaService consultarMoneda = new ConsultarMonedaService();
        ConversorMoneda conversorMoneda = new ConversorMoneda();

        List<String> historial = new ArrayList<>();

        while (true){
            System.out.println("""
                    \n*******************************************************************************
                    Bienvenido a la aplicación para conversión de monedas
                    Seleccione la moneda de origen:
                    1. ARS - Peso argentino
                    2. BOB - Boliviano boliviano
                    3. BRL - Real brasileño
                    4. CLP - Peso chileno
                    5. COP - Peso colombiano
                    6. USD - Dólar estadounidense
                    7. Ver historial de conversiones
                    8. Salir
                    ->""");
            int opcionPartida = scanner.nextInt();

            if (opcionPartida == 8){
                System.out.println("""
                        \nSeleccionó la opción 8
                        Saliendo de la aplicación
                        *******************************************************************************
                        """);
                break;
            }

            if (opcionPartida == 7){
                System.out.println("\n*********** HISTORIAL DE CONVERSIONES ***********");
                if (historial.isEmpty()) {
                    System.out.println("No se han realizado conversiones aún.");
                } else {
                    for (String registro : historial) {
                        System.out.println(registro);
                    }
                }
                System.out.println("***********************************************\n");
                continue;
            }

            String monedaPartida;

            switch (opcionPartida) {
                case 1: monedaPartida = "ARS"; break;
                case 2: monedaPartida = "BOB"; break;
                case 3: monedaPartida = "BRL"; break;
                case 4: monedaPartida = "CLP"; break;
                case 5: monedaPartida = "COP"; break;
                case 6: monedaPartida = "USD"; break;
                default:
                    System.out.println("""
                            La opción seleccionada no es válida.
                            Seleccione una opción válida.
                    """);
                    continue;
            }

            System.out.println("""
                    \nAhora seleccione la moneda de destino:
                    1. ARS - Peso argentino
                    2. BOB - Boliviano boliviano
                    3. BRL - Real brasileño
                    4. CLP - Peso chileno
                    5. COP - Peso colombiano
                    6. USD - Dólar estadounidense
                    ->""");
            int opcionDestino = scanner.nextInt();

            String monedaDestino;

            switch (opcionDestino) {
                case 1: monedaDestino = "ARS"; break;
                case 2: monedaDestino = "BOB"; break;
                case 3: monedaDestino = "BRL"; break;
                case 4: monedaDestino = "CLP"; break;
                case 5: monedaDestino = "COP"; break;
                case 6: monedaDestino = "USD"; break;
                default:
                    System.out.println("""
                            La opción seleccionada no es válida.
                            Seleccione una opción válida.
                    """);
                    continue;
            }

            ConsultarMonedaAPI consultaMonedaPartida = consultarMoneda.getMoneda(monedaPartida);
            MonedaDTO monedaOrigen = new MonedaDTO(consultaMonedaPartida);

            ConsultarMonedaAPI consultarMonedaDestino = consultarMoneda.getMoneda(monedaDestino);
            MonedaDTO monedaConvertida = new MonedaDTO(consultarMonedaDestino);

            System.out.printf("""
                    *******************************************************************************
                    Ingrese la cantidad que quiere convertir de   %s   a   %s  :
                    %n""", monedaOrigen.getCurrency(), monedaConvertida.getCurrency());

            DecimalFormat df = new DecimalFormat("#.##");
            double cantidad = scanner.nextDouble();
            String cantidadText = df.format(cantidad);
            double tasaConversion = monedaOrigen.getConversionRate(monedaConvertida.getCurrency());
            double cantidadConvertida = conversorMoneda.convertir(cantidad, tasaConversion);
            String cantidadConvertidaText = df.format(cantidadConvertida);

            String registro = String.format("%s %s equivalen a %s %s",
                    cantidadText, monedaOrigen.getCurrency(), cantidadConvertidaText, monedaConvertida.getCurrency());
            historial.add(registro);

            System.out.printf("""
            *******************************************************************************
                %s
            *******************************************************************************
            %n""", registro);
        }
    }
}