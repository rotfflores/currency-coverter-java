package com.rotflores.projects.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rotflores.projects.modulo.Calculos;
import com.rotflores.projects.modulo.Currency;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) throws IOException, InterruptedException {
        String link = "https://v6.exchangerate-api.com/v6/cb70723827cee8b0ce31ba83/latest/USD";
        Scanner typing = new Scanner(System.in);
        int opcionMenu = 0;

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, Object> jsonResponse = gson.fromJson(response.body(), Map.class);
        Map<String, Double> conversionRates = (Map<String, Double>) jsonResponse.get("conversion_rates");

        Currency currency = new Currency();
        currency.setArg(conversionRates.get("ARS"));
        currency.setBrl(conversionRates.get("BRL"));
        currency.setClp(conversionRates.get("CLP"));
        currency.setUsd(conversionRates.get("USD"));

        Calculos calculos = new Calculos(currency);

        String menu = """
                **********************************
                Sea bienvenido/a al conversor de moneda
                
                1- Dolar ==> Peso argentino
                2- Peso argentino ==> Dolar
                3- Dolar ==> Real brasileño
                4- Real brasileño ==> dolar
                5- Dolar ==> Peso chileno
                6- Peso chileno ==> Dolar
                7- Salir
                Elija la opcion valida
                **********************************
                """;

        while (opcionMenu != 7) {
            System.out.println(menu);
            opcionMenu = typing.nextInt();
            double cantidad;
            switch (opcionMenu) {
                case 1:
                    System.out.println("Ingrese la cantidad que desee convertir a pesos argentinos");
                    cantidad = typing.nextDouble();
                    currency.setCuantity(cantidad);
                    System.out.println("La cantidad en pesos argentinos es: " + calculos.usdToArg());
                    break;
                case 2:
                    System.out.println("Ingrese la cantidad que desee convertir a dólares");
                    cantidad = typing.nextDouble();
                    currency.setCuantity(cantidad);
                    System.out.println("La cantidad en dólares es: " + calculos.convert("arg", "usd"));
                    break;
                case 3:
                    System.out.println("Ingrese la cantidad que desee convertir a reales brasileños");
                    cantidad = typing.nextDouble();
                    currency.setCuantity(cantidad);
                    System.out.println("La cantidad en reales brasileños es: " + calculos.usdToBrl());
                    break;
                case 4:
                    System.out.println("Ingrese la cantidad que desee convertir a dólares");
                    cantidad = typing.nextDouble();
                    currency.setCuantity(cantidad);
                    System.out.println("La cantidad en dólares es: " + calculos.convert("brl", "usd"));
                    break;
                case 5:
                    System.out.println("Ingrese la cantidad que desee convertir a pesos chilenos");
                    cantidad = typing.nextDouble();
                    currency.setCuantity(cantidad);
                    System.out.println("La cantidad en pesos chilenos es: " + calculos.usdToClp());
                    break;
                case 6:
                    System.out.println("Ingrese la cantidad que desee convertir a dólares");
                    cantidad = typing.nextDouble();
                    currency.setCuantity(cantidad);
                    System.out.println("La cantidad en dólares es: " + calculos.convert("clp", "usd"));
                    break;
                case 7:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }
        typing.close();
    }
}
