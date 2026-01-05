package club;

public class Factura {

    private String nombre;
    private String concepto;
    private double valor;

    public Factura(String nombre, String concepto, double valor) {
        this.nombre = nombre;
        this.concepto = concepto;
        this.valor = valor;
    }

    public String darNombre() { return nombre; }
    public String darConcepto() { return concepto; }
    public double darValor() { return valor; }

    @Override
    public String toString() {
        return concepto + " - $" + valor + " (" + nombre + ")";
    }
}
