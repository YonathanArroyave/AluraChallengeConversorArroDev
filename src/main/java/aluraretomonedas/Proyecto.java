package aluraretomonedas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

/**
 *
 * @author arro.dev
 */
public class Proyecto {

    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            // Mostrar menú
            System.out.println("\n=== CONVERSOR DE DIVISAS ===");
            System.out.println("1. COP -> USD");
            System.out.println("2. COP -> EUR");
            System.out.println("3. USD -> COP");
            System.out.println("4. EUR -> COP");
            System.out.println("5. Escribir pares personalizados");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            String divisa1 = "";
            String divisa2 = "";

            switch (opcion) {
                case 1:
                    divisa1 = "COP";
                    divisa2 = "USD";
                    realizarConversion(divisa1, divisa2);
                    break;
                case 2:
                    divisa1 = "COP";
                    divisa2 = "EUR";
                    realizarConversion(divisa1, divisa2);
                    break;
                case 3:
                    divisa1 = "USD";
                    divisa2 = "COP";
                    realizarConversion(divisa1, divisa2);
                    break;
                case 4:
                    divisa1 = "EUR";
                    divisa2 = "COP";
                    realizarConversion(divisa1, divisa2);
                    break;
                case 5:
                    realizarConversionPersonalizada();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("¡Gracias por usar el conversor!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void realizarConversion(String divisa1, String divisa2) throws IOException, InterruptedException {
        Scanner cantidad = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        System.out.println("Escriba cantidad en " + divisa1 + " :");
        var cant = cantidad.nextLine();

        // Crear formateadores de moneda según la divisa
        NumberFormat formatoDivisa1 = obtenerFormato(divisa1);
        NumberFormat formatoDivisa2 = obtenerFormato(divisa2);

        String direccion = "https://v6.exchangerate-api.com/v6/52340389e50330e818f09e82/pair/"
                + divisa1 + "/" + divisa2 + "/" + cant.replaceAll("[.,]", "");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();
        double cantidadOriginal = Double.parseDouble(cant.replaceAll("[.,]", ""));

        System.out.println(divisa1 + " " + formatoDivisa1.format(cantidadOriginal)
                + " = " + divisa2 + " " + formatoDivisa2.format(resultadoConversion));
    }

    private static void realizarConversionPersonalizada() throws IOException, InterruptedException {
        System.out.println("POR FAVOR NO INTRODUZCA NI PUNTOS NI COMAS EN LAS CANTIDADES.");
        Scanner entrada1 = new Scanner(System.in);
        Scanner entrada2 = new Scanner(System.in);
        Scanner cantidad = new Scanner(System.in);

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        System.out.println("Escriba la Divisa a la cual desea convertir : ");
        var divisa1 = entrada1.nextLine().toUpperCase();

        System.out.println("Escriba cantidad en " + divisa1 + " :");
        var cant = cantidad.nextLine();

        System.out.println("Escriba la Divisa de entrada 2 : ");
        var divisa2 = entrada2.nextLine().toUpperCase();

        NumberFormat formatoDivisa1 = obtenerFormato(divisa1);
        NumberFormat formatoDivisa2 = obtenerFormato(divisa2);

        String direccion = "https://v6.exchangerate-api.com/v6/52340389e50330e818f09e82/pair/"
                + divisa1 + "/" + divisa2 + "/" + cant.replaceAll("[.,]", "");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        String json = response.body();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        double resultadoConversion = jsonObject.get("conversion_result").getAsDouble();
        double cantidadOriginal = Double.parseDouble(cant.replaceAll("[.,]", ""));

        System.out.println(divisa1 + " " + formatoDivisa1.format(cantidadOriginal)
                + " = " + divisa2 + " " + formatoDivisa2.format(resultadoConversion));
    }

    private static NumberFormat obtenerFormato(String divisa) {
        NumberFormat formato;
        switch (divisa) {
            case "COP":
                formato = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
                break;
            case "USD":
                formato = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
                break;
            case "EUR":
                formato = NumberFormat.getCurrencyInstance(new Locale("es", "ES"));
                break;
            default:
                formato = NumberFormat.getInstance();
                formato.setMaximumFractionDigits(2);
                formato.setMinimumFractionDigits(2);
        }
        return formato;
    }
}
