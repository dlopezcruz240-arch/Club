package club;

import java.util.Scanner;
import club.Socio.Tipo;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Club club = new Club();
        int opcion = 0;

        do {
            System.out.println("\n--- CLUB SOCIAL ---");
            System.out.println("1. Afiliar socio");
            System.out.println("2. Agregar autorizado");
            System.out.println("3. Pagar factura");
            System.out.println("4. Registrar consumo");
            System.out.println("5. Aumentar fondos");
            System.out.println("6. Salir");
            System.out.print("Opción: ");

            opcion = sc.nextInt();

            try {
                switch (opcion) {

                    case 1: // Afiliar socio
                        System.out.print("Cédula: ");
                        String cedula = sc.next();

                        System.out.print("Nombre: ");
                        String nombre = sc.next();

                        System.out.print("Tipo (VIP/REGULAR): ");
                        Tipo tipo = Tipo.valueOf(sc.next().toUpperCase());

                        club.afiliarSocio(cedula, nombre, tipo);
                        System.out.println("Socio afiliado correctamente");
                        break;

                    case 2: // Agregar autorizado
                        System.out.print("Cédula del socio: ");
                        String cedulaSocio = sc.next();

                        System.out.print("Nombre del autorizado: ");
                        String nombreAutorizado = sc.next();

                        club.agregarAutorizadoSocio(cedulaSocio, nombreAutorizado);
                        System.out.println("Autorizado agregado correctamente");
                        break;

                    case 3: // Pagar factura
                        System.out.print("Cédula del socio: ");
                        String cedulaPago = sc.next();

                        System.out.print("Índice de la factura: ");
                        int indice = sc.nextInt();

                        club.pagarFacturaSocio(cedulaPago, indice);
                        System.out.println("Factura pagada correctamente");
                        break;

                    case 4: // Registrar consumo
                        System.out.print("Cédula del socio: ");
                        String cedulaConsumo = sc.next();

                        System.out.print("Nombre del consumidor: ");
                        String nombreConsumo = sc.next();

                        System.out.print("Concepto del consumo: ");
                        String concepto = sc.next();

                        System.out.print("Valor del consumo: ");
                        double valor = sc.nextDouble();

                        club.registrarConsumo(
                                cedulaConsumo,
                                nombreConsumo,
                                concepto,
                                valor
                        );
                        System.out.println("Consumo registrado correctamente");
                        break;

                    case 5: // Aumentar fondos
                        System.out.print("Cédula del socio: ");
                        String cedulaFondos = sc.next();

                        System.out.print("Valor a aumentar: ");
                        double valorFondos = sc.nextDouble();

                        club.aumentarFondosSocio(cedulaFondos, valorFondos);
                        System.out.println("Fondos aumentados correctamente");
                        break;

                    case 6:
                        System.out.println("Gracias por usar el sistema");
                        break;

                    default:
                        System.out.println("Opción inválida");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 6);

        sc.close();
    }
}
