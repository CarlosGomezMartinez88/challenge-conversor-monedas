package com.alura.conversormonedas;

import com.alura.conversormonedas.converter.ConversorMoneda;
import com.alura.conversormonedas.dto.MonedaDTO;
import com.alura.conversormonedas.model.ConsultarMonedaAPI;
import com.alura.conversormonedas.service.ConsultarMonedaService;

import java.text.DecimalFormat;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);

        ConsultarMonedaService consultarMoneda = new ConsultarMonedaService();
        ConversorMoneda conversorMoneda = new ConversorMoneda();

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
                    7. Salir
                    ->""");
            int opcionPartida = scanner.nextInt();

            if (opcionPartida == 7){
                System.out.println("""
                        \nSeleccionó la opción 7
                        Saliendo de la aplicación
                        *******************************************************************************
                        """);
                break;
            }

            String monedaPartida;

            switch (opcionPartida) {
                case 1:
                    monedaPartida = "ARS";
                    break;
                case 2:
                    monedaPartida = "BOB";
                    break;
                case 3:
                    monedaPartida = "BRL";
                    break;
                case 4:
                    monedaPartida = "CLP";
                    break;
                case 5:
                    monedaPartida = "COP";
                    break;
                case 6:
                    monedaPartida = "USD";
                    break;
                default:
                    System.out.println("""
                            La opción seleccionada no es válida.\s
                            Seleccione una opción válida.
                           \s""");
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
                    7. Salir
                    ->""");
            int opcionDestino = scanner.nextInt();

            if (opcionDestino == 7){
                System.out.println("""
                        \nSeleccionó la opción 7
                        Saliendo de la aplicación
                        *******************************************************************************
                        """);
                break;
            }

            String monedaDestino;

            switch (opcionDestino) {
                case 1:
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaDestino = "BOB";
                    break;
                case 3:
                    monedaDestino = "BRL";
                    break;
                case 4:
                    monedaDestino = "CLP";
                    break;
                case 5:
                    monedaDestino = "COP";
                    break;
                case 6:
                    monedaDestino = "USD";
                    break;
                default:
                    System.out.println("""
                            La opción seleccionada no es válida.\s
                            Seleccione una opción válida.
                           \s""");
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

            System.out.printf("""
            *******************************************************************************
                %s  %s  equivalen a   %s %s
            *******************************************************************************
            %n""", cantidadText, monedaOrigen.getCurrency(), cantidadConvertidaText, monedaConvertida.getCurrency());
        }
    }
}