// Archivo ResultadoConversion.java (Clase de datos)
package aluraretomonedas;
public class ResultadoConversion {
    private final String divisa1;
    private final String divisa2;
    private final double cantidadOriginal;
    private final double cantidadConvertida;

    public ResultadoConversion(String divisa1, String divisa2, double cantidadOriginal, double cantidadConvertida) {
        this.divisa1 = divisa1;
        this.divisa2 = divisa2;
        this.cantidadOriginal = cantidadOriginal;
        this.cantidadConvertida = cantidadConvertida;
    }

    // Getters
    public String getDivisa1() { return divisa1; }
    public String getDivisa2() { return divisa2; }
    public double getCantidadOriginal() { return cantidadOriginal; }
    public double getCantidadConvertida() { return cantidadConvertida; }
}