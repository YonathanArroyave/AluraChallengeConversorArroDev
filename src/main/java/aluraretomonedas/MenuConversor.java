// Archivo MenuConversor.java (Manejo del menú)
package aluraretomonedas;
import java.io.IOException;
import java.util.Scanner;

public class MenuConversor {
    private final Scanner scanner;
    private final ServicioConversion servicioConversion;

    public MenuConversor() {
        this.scanner = new Scanner(System.in);
        this.servicioConversion = new ServicioConversion();
    }

    public void iniciar() throws IOException, InterruptedException {
        boolean continuar = true;

        while (continuar) {
            mostrarMenu();
            int opcion = obtenerOpcion();
            continuar = procesarOpcion(opcion);
        }
    }

    private void mostrarMenu() {
        System.out.println("\n=== CONVERSOR DE DIVISAS ===");
        System.out.println("1. COP -> USD");
        System.out.println("2. COP -> EUR");
        System.out.println("3. USD -> COP");
        System.out.println("4. EUR -> COP");
        System.out.println("5. Escribir pares personalizados");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private int obtenerOpcion() {
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return opcion;
    }

    private boolean procesarOpcion(int opcion) throws IOException, InterruptedException {
        switch (opcion) {
            case 1:
                servicioConversion.realizarConversion("COP", "USD");
                return true;
            case 2:
                servicioConversion.realizarConversion("COP", "EUR");
                return true;
            case 3:
                servicioConversion.realizarConversion("USD", "COP");
                return true;
            case 4:
                servicioConversion.realizarConversion("EUR", "COP");
                return true;
            case 5:
                servicioConversion.realizarConversionPersonalizada();
                return true;
            case 6:
                System.out.println("¡Gracias por usar el conversor!");
                return false;
            default:
                System.out.println("Opción no válida. Por favor, intente de nuevo.");
                return true;
        }
    }
}