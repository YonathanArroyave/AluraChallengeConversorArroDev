package aluraretomonedas;

// Archivo ConversorDivisas.java (Clase principal)
import java.io.IOException;

public class ConversorDivisas {
    public static void main(String[] args) {
        MenuConversor menu = new MenuConversor();
        try {
            menu.iniciar();
        } catch (IOException | InterruptedException e) {
            System.out.println("Error en la aplicación: " + e.getMessage());
        }
    }
}