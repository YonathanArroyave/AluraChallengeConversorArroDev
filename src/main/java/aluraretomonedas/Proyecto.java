package aluraretomonedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 *
 * @author arro
 */
public class Proyecto {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("POR FAVOR NO INTRUDUZCA NI PUNTOS NI COMAS EN LAS CANTIDADES.");
        Scanner entrada1 = new Scanner(System.in);
        Scanner entrada2 = new Scanner(System.in);
        Scanner cantidad = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        System.out.println("Escriba la Divisa a la cual desea convertir : ");
        var divisa1 = entrada1.nextLine();

        System.out.println("Escriba cantidad en " + divisa1 + " :");
        var cant = cantidad.nextLine();

        System.out.println("Escriba la Divisa de entrada 2 : ");
        var divisa2 = entrada2.nextLine();

        String direcion = "https://v6.exchangerate-api.com/v6/52340389e50330e818f09e82/pair/" + divisa1 + "/" + divisa2 + "/" + cant.replaceAll("[.,]", "");
        System.out.println(direcion);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direcion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        //System.out.println(json);

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();

        //Ahora puedes usar resultadoConversion para tus operaciones
        System.out.println("El resultado de la conversión es: " + resultadoConversion);
    }
}
