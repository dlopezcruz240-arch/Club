package club;

import java.util.ArrayList;
import club.Socio.Tipo;

public class Club {

    public static final int MAXIMO_VIP = 3;
    private ArrayList<Socio> socios;

    public Club() {
        socios = new ArrayList<>();
    }

    public ArrayList<Socio> darSocios() {
        return socios;
    }

    public void afiliarSocio(String cedula, String nombre, Tipo tipo) throws ClubException {
        if (buscarSocio(cedula) != null) {
            throw new ClubException("El socio ya existe");
        }

        if (tipo == Tipo.VIP && contarSociosVIP() >= MAXIMO_VIP) {
            throw new ClubException("Máximo de socios VIP alcanzado");
        }

        socios.add(new Socio(cedula, nombre, tipo));
    }

    public Socio buscarSocio(String cedula) {
        for (Socio s : socios) {
            if (s.darCedula().equals(cedula)) {
                return s;
            }
        }
        return null;
    }

    private int contarSociosVIP() {
        int contador = 0;
        for (Socio s : socios) {
            if (s.darTipo() == Tipo.VIP) {
                contador++;
            }
        }
        return contador;
    }

    public void agregarAutorizadoSocio(String cedula, String nombre) throws ClubException {
        Socio s = buscarSocio(cedula);
        if (s == null) {
            throw new ClubException("Socio no existe");
        }
        s.agregarAutorizado(nombre);
    }

    public void eliminarAutorizadoSocio(String cedula, String nombre) throws ClubException {
        Socio s = buscarSocio(cedula);
        if (s == null) {
            throw new ClubException("Socio no existe");
        }
        s.eliminarAutorizado(nombre);
    }

    public void registrarConsumo(String cedula, String nombre, String concepto, double valor)
            throws ClubException {
        Socio s = buscarSocio(cedula);
        if (s == null) {
            throw new ClubException("Socio no existe");
        }
        s.registrarConsumo(nombre, concepto, valor);
    }

    public void pagarFacturaSocio(String cedula, int indice) throws ClubException {
        Socio s = buscarSocio(cedula);
        if (s == null) {
            throw new ClubException("Socio no existe");
        }
        s.pagarFactura(indice);
    }

    public void aumentarFondosSocio(String cedula, double valor) throws ClubException {
        Socio s = buscarSocio(cedula);
        if (s == null) {
            throw new ClubException("Socio no existe");
        }
        s.aumentarFondos(valor);
    }

    // 🔹 Método solicitado en el documento
    public double totalConsumosSocio(String cedula) throws ClubException {
        Socio s = buscarSocio(cedula);
        if (s == null) {
            throw new ClubException("No existe socio con esa cédula");
        }

        double total = 0;
        for (Factura f : s.darFacturas()) {
            total += f.darValor();
        }
        return total;
    }

    // 🔹 Método solicitado en el documento
    public boolean sePuedeEliminarSocio(String cedula) throws ClubException {
        Socio s = buscarSocio(cedula);

        if (s == null) {
            throw new ClubException("No existe socio con esa cédula");
        }

        if (s.darTipo() == Tipo.VIP) {
            return false;
        }

        if (!s.darFacturas().isEmpty()) {
            return false;
        }

        if (s.darAutorizados().size() > 1) {
            return false;
        }

        return true;
    }
}

