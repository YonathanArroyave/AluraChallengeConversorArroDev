// Archivo ServicioConversion.java (Lógica de conversión)
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

public class ServicioConversion {
    private static final String API_KEY = "52340389e50330e818f09e82";
    private final Scanner scanner;
    private final Gson gson;
    private final FormateadorMoneda formateadorMoneda;

    public ServicioConversion() {
        this.scanner = new Scanner(System.in);
        this.gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();
        this.formateadorMoneda = new FormateadorMoneda();
    }

    public void realizarConversion(String divisa1, String divisa2) throws IOException, InterruptedException {
        System.out.println("Escriba cantidad en " + divisa1 + " :");
        String cantidad = scanner.nextLine();
        
        ResultadoConversion resultado = convertirDivisas(divisa1, divisa2, cantidad);
        mostrarResultado(resultado);
    }

    public void realizarConversionPersonalizada() throws IOException, InterruptedException {
        System.out.println("POR FAVOR NO INTRODUZCA NI PUNTOS NI COMAS EN LAS CANTIDADES.");
        
        System.out.println("Escriba la Divisa a la cual desea convertir : ");
        String divisa1 = scanner.nextLine().toUpperCase();
        
        System.out.println("Escriba cantidad en " + divisa1 + " :");
        String cantidad = scanner.nextLine();
        
        System.out.println("Escriba la Divisa de entrada 2 : ");
        String divisa2 = scanner.nextLine().toUpperCase();

        ResultadoConversion resultado = convertirDivisas(divisa1, divisa2, cantidad);
        mostrarResultado(resultado);
    }

    private ResultadoConversion convertirDivisas(String divisa1, String divisa2, String cantidad) 
            throws IOException, InterruptedException {
        String cantidadLimpia = cantidad.replaceAll("[.,]", "");
        String url = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s",
                API_KEY, divisa1, divisa2, cantidadLimpia);

        String json = realizarPeticionHttp(url);
        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();
        double cantidadOriginal = Double.parseDouble(cantidadLimpia);

        return new ResultadoConversion(divisa1, divisa2, cantidadOriginal, resultadoConversion);
    }

    private String realizarPeticionHttp(String url) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    private void mostrarResultado(ResultadoConversion resultado) {
        String mensajeFormateado = String.format("%s %s = %s %s",
                resultado.getDivisa1(),
                formateadorMoneda.formatear(resultado.getDivisa1(), resultado.getCantidadOriginal()),
                resultado.getDivisa2(),
                formateadorMoneda.formatear(resultado.getDivisa2(), resultado.getCantidadConvertida()));
        
        System.out.println(mensajeFormateado);
    }
}