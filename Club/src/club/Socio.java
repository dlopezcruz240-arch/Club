package club;

import java.util.ArrayList;

public class Socio {

    public enum Tipo {
        VIP, REGULAR
    }

    public static final double FONDOS_INICIALES_REGULAR = 50;
    public static final double FONDOS_INICIALES_VIP = 100;
    public static final double MAX_REGULAR = 1000;
    public static final double MAX_VIP = 5000;

    private String cedula;
    private String nombre;
    private double fondos;
    private Tipo tipo;
    private ArrayList<Factura> facturas;
    private ArrayList<String> autorizados;

    public Socio(String cedula, String nombre, Tipo tipo) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.tipo = tipo;
        fondos = (tipo == Tipo.VIP) ? FONDOS_INICIALES_VIP : FONDOS_INICIALES_REGULAR;
        facturas = new ArrayList<>();
        autorizados = new ArrayList<>();
    }

    public String darCedula() { return cedula; }
    public String darNombre() { return nombre; }
    public double darFondos() { return fondos; }
    public Tipo darTipo() { return tipo; }
    public ArrayList<Factura> darFacturas() { return facturas; }
    public ArrayList<String> darAutorizados() { return autorizados; }

    public void aumentarFondos(double valor) throws ClubException {
        double max = (tipo == Tipo.VIP) ? MAX_VIP : MAX_REGULAR;
        if (fondos + valor > max) {
            throw new ClubException("Se excede el monto máximo permitido");
        }
        fondos += valor;
    }

    public void registrarConsumo(String nombre, String concepto, double valor) throws ClubException {
        if (valor > fondos) {
            throw new ClubException("Fondos insuficientes");
        }
        facturas.add(new Factura(nombre, concepto, valor));
    }

    public void pagarFactura(int indice) throws ClubException {
        if (indice < 0 || indice >= facturas.size()) {
            throw new ClubException("Factura inválida");
        }

        Factura f = facturas.get(indice);
        if (f.darValor() > fondos) {
            throw new ClubException("Fondos insuficientes");
        }

        fondos -= f.darValor();
        facturas.remove(indice);
    }

    public void agregarAutorizado(String nombre) throws ClubException {
        if (fondos <= 0) {
            throw new ClubException("Fondos insuficientes");
        }
        if (autorizados.contains(nombre)) {
            throw new ClubException("El autorizado ya existe");
        }
        autorizados.add(nombre);
    }

    public void eliminarAutorizado(String nombre) throws ClubException {
        for (Factura f : facturas) {
            if (f.darNombre().equals(nombre)) {
                throw new ClubException("El autorizado tiene facturas pendientes");
            }
        }

        if (!autorizados.remove(nombre)) {
            throw new ClubException("El autorizado no existe");
        }
    }
}
