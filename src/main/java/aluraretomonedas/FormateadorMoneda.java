// Archivo FormateadorMoneda.java (Formateo de monedas)
package aluraretomonedas;
import java.text.NumberFormat;
import java.util.Locale;

public class FormateadorMoneda {
    public String formatear(String divisa, double cantidad) {
        return obtenerFormato(divisa).format(cantidad);
    }

    private NumberFormat obtenerFormato(String divisa) {
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