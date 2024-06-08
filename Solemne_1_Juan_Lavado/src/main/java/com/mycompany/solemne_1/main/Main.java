package com.mycompany.solemne_1.main;

/**
 *
 * @author juan
 */
import com.mycompany.solemne_1.celular.Celular;
import com.mycompany.solemne_1.celular.ClienteCelular;
import com.mycompany.solemne_1.companiatelefono.CompaniaTelefono;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CompaniaTelefono compania = new CompaniaTelefono("TTCC", 100); // la capacidad que le dimos

        // Agregar clientes de prueba
        agregarClientesDePrueba(compania);

        int option;
        do {
            mostrarMenu();
            System.out.print("Seleccione una opción: ");
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        buscarCliente(scanner, compania);
                        break;
                    case 2:
                        listarNumeroClientas(compania);
                        break;
                    case 3:
                        editarCliente(scanner, compania);
                        break;
                    case 4:
                        listarClientes(compania);
                        break;
                    case 5:
                        agregarNuevoCliente(scanner, compania);
                        break;
                    case 6:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número entre 1 y 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número.");
                scanner.nextLine(); // Limpiamos el buffer en el scanner
                option = 0; // Mantener el bucle
            }
        } while (option != 6);

        scanner.close();
    }

    /**
     * Ordenamos nuestra consola y permitimos input.
     * 
     * Mostramos de manera ordenada el output.
     */
    private static void mostrarMenu() {
        System.out.println("\n--- Compañía Móvil TTCC ---");
        System.out.println("1. Buscar cliente por RUT");
        System.out.println("2. Listar número de clientas (sexo F)");
        System.out.println("3. Editar cliente");
        System.out.println("4. Listar todos los clientes");
        System.out.println("5. Agregar nuevo cliente");
        System.out.println("6. Salir");
        System.out.println("-------------");
    }

    private static void buscarCliente(Scanner scanner, CompaniaTelefono compania) {
        System.out.print("Ingrese el RUT del cliente: ");
        String rut = scanner.nextLine();

        ClienteCelular cliente = compania.buscarCliente(rut);
        if (cliente == null) {
            System.out.println("No existe.");
        } else {
            System.out.println("\n--- Información del Cliente ---");
            System.out.println("Número de celular: " + cliente.getCelular().getNumero());
            System.out.println("Nombre del cliente: " + cliente.getNombre());
            System.out.println("Tiene plan: " + (cliente.isTienePlan() ? "Sí" : "No"));
            System.out.println("-------------------------------");
        }
    }

    private static void listarNumeroClientas(CompaniaTelefono compania) {
        System.out.println("\n--- Número de Clientas ---");
        int numeroClientas = compania.obtenerNumeroClientas();
        System.out.println("Número de clientas (sexo F): " + numeroClientas);
        System.out.println("----------------------------");
    }

    private static void editarCliente(Scanner scanner, CompaniaTelefono compania) {
        System.out.print("Ingrese el RUT del cliente a editar: ");
        String rut = scanner.nextLine();

        ClienteCelular cliente = compania.buscarCliente(rut);
        if (cliente == null) {
            System.out.println("El cliente no existe.");
        } else {
            String nuevoNumero;
            boolean numeroValido;
            do {
                numeroValido = true;
                System.out.print("Ingrese el nuevo número de celular (8 dígitos): ");
                nuevoNumero = scanner.nextLine();

                if (nuevoNumero.length() != 8) {
                    System.out.print("Número inválido. Ingrese nuevamente (8 dígitos): ");
                    numeroValido = false;
                } else if (compania.numeroRegistrado(nuevoNumero)) {
                    System.out.print("Este número ya está registrado para otro cliente. Ingrese un número diferente: ");
                    numeroValido = false;
                }
            } while (!numeroValido);

            cliente.getCelular().setNumero(nuevoNumero);
            System.out.println("Número de celular actualizado con éxito.");
        }
    }

    private static void listarClientes(CompaniaTelefono compania) {
        System.out.println("\n--- Listar Todos los Clientes ---");
        int count = 1;
        for (ClienteCelular cliente : compania.getClientes()) {
            System.out.println("Cliente " + count + ": " + cliente);
            count++;
        }
        System.out.println("---------------------------------");
    }

    private static void agregarNuevoCliente(Scanner scanner, CompaniaTelefono compania) {
        System.out.println("Ingrese los datos del nuevo cliente:");

        String rut;
        do {
            System.out.print("RUT: ");
            rut = scanner.nextLine();
            if (compania.buscarClienteBoolean(rut)) {
                System.out.println("El cliente ya existe. Ingrese un RUT diferente.");
            }
        } while (compania.buscarClienteBoolean(rut));

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        int edad;
        do {
            System.out.print("Edad: ");
            edad = scanner.nextInt();
            if (edad < 18) {
                System.out.println("La edad debe ser mayor o igual a 18. Ingrese una edad válida.");
            }
        } while (edad < 18);

        scanner.nextLine(); // limpiamos buffer
        System.out.print("Sexo (M/F): ");
        char sexo = scanner.nextLine().charAt(0);

        boolean tienePlan = false;
        while (true) {
            System.out.print("Tiene plan (Sí/No): ");
            String planInput = scanner.nextLine().trim().toLowerCase();
            if (planInput.equals("sí") || planInput.equals("si")) {
                tienePlan = true;
                break;
            } else if (planInput.equals("no")) {
                tienePlan = false;
                break;
            } else {
                System.out.println("Entrada inválida. Por favor, ingrese 'Sí' o 'No'.");
            }
        }

        String numeroCelular;
        do {
            System.out.print("Número de celular: ");
            numeroCelular = scanner.nextLine();
            try {
                new Celular(numeroCelular, "");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                numeroCelular = "";
            }
            if (compania.numeroRegistrado(numeroCelular)) {
                System.out.print("Este número ya está registrado para otro cliente. Ingrese un número diferente: ");
                numeroCelular = "";
            }
        } while (numeroCelular.length() != 8 || compania.numeroRegistrado(numeroCelular));

        System.out.print("Marca de celular: ");
        String marca = scanner.nextLine();

        Celular celular = new Celular(numeroCelular, marca);
        ClienteCelular nuevoCliente = new ClienteCelular(rut, nombre, edad, sexo, tienePlan, celular);

        if (compania.agregarCliente(nuevoCliente)) {
            System.out.println("Cliente agregado con éxito.");
        }
    }

    private static void agregarClientesDePrueba(CompaniaTelefono compania) {
        compania.agregarCliente(
                new ClienteCelular("12345678-9", "Ana", 30, 'F', true, new Celular("87654321", "Samsung")));
        compania.agregarCliente(
                new ClienteCelular("98765432-1", "Pedro", 40, 'M', false, new Celular("12345678", "Apple")));
        compania.agregarCliente(
                new ClienteCelular("45678901-2", "Maria", 25, 'F', true, new Celular("23456789", "Nokia")));
    }
}