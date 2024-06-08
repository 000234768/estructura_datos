package com.mycompany.solemne_1.companiatelefono;

/**
 *
 * @author juan
 */
import com.mycompany.solemne_1.celular.ClienteCelular;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CompaniaTelefono {
    private String nombre;
    private List<ClienteCelular> clientes;
    private int capacidadMaxima;

    /**
     * Constructor que inicializa la compañía con un nombre y una capacidad máxima
     * de clientes.
     * 
     * @param nombre          Nombre de la compañía.
     * @param capacidadMaxima Capacidad máxima de clientes que puede tener la
     *                        compañía.
     */
    public CompaniaTelefono(String nombre, int capacidadMaxima) {
        this.nombre = nombre;
        this.capacidadMaxima = capacidadMaxima;
        this.clientes = new ArrayList<>();
    }

    /**
     * Agrega un cliente a la compañía, validando que no supere la capacidad máxima,
     * que no exista un cliente con el mismo RUT y que el número de celular no esté
     * ya registrado.
     * 
     * @param cliente El cliente a agregar.
     * @return true si el cliente se agregó exitosamente, false en caso contrario.
     */
    public boolean agregarCliente(ClienteCelular cliente) {
        if (clientes.size() >= capacidadMaxima) {
            System.out.println("Capacidad máxima alcanzada. No se puede agregar más clientes.");
            return false;
        }
        if (buscarClienteBoolean(cliente.getRut())) {
            System.out.println("Un cliente con este RUT ya existe.");
            return false;
        }
        if (numeroRegistrado(cliente.getCelular().getNumero())) {
            System.out.println("Este número de celular ya está registrado para otro cliente.");
            return false;
        }
        clientes.add(cliente);
        System.out.println("Cliente almacenado.");
        return true;
    }

    /**
     * Busca un cliente por su RUT.
     * 
     * @param rut El RUT del cliente a buscar.
     * @return El cliente si se encuentra, null en caso contrario.
     */
    public ClienteCelular buscarCliente(String rut) {
        for (ClienteCelular cliente : clientes) {
            if (cliente.getRut().equals(rut)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Verifica si existe un cliente con un RUT específico.
     * 
     * @param rut El RUT a verificar.
     * @return true si el cliente existe, false en caso contrario.
     */
    public boolean buscarClienteBoolean(String rut) {
        return buscarCliente(rut) != null;
    }

    /**
     * Cambia el número de celular de un cliente, si el cliente existe y el nuevo
     * número no está registrado.
     * 
     * @param rut         El RUT del cliente.
     * @param nuevoNumero El nuevo número de celular.
     * @return true si el número se cambió exitosamente, false en caso contrario.
     */
    public boolean cambiarCelular(String rut, String nuevoNumero) {
        ClienteCelular cliente = buscarCliente(rut);
        if (cliente != null && !numeroRegistrado(nuevoNumero)) {
            cliente.getCelular().setNumero(nuevoNumero);
            return true;
        }
        return false;
    }

    /**
     * Lista todos los clientes ordenados por nombre.
     */
    public void listarClientes() {
        Collections.sort(clientes, Comparator.comparing(ClienteCelular::getNombre));
        int count = 1;
        for (ClienteCelular cliente : clientes) {
            System.out.println("Cliente " + count + ": " + cliente);
            count++;
        }
    }

    /**
     * Obtiene el número de clientas (sexo F) en la compañía.
     * 
     * @return El número de clientas.
     */
    public int obtenerNumeroClientas() {
        int count = 0;
        for (ClienteCelular cliente : clientes) {
            if (cliente.getSexo() == 'F') {
                count++;
            }
        }
        return count;
    }

    /**
     * Verifica si un número de celular ya está registrado para otro cliente.
     * 
     * @param numero El número de celular a verificar.
     * @return true si el número está registrado, false en caso contrario.
     */
    public boolean numeroRegistrado(String numero) {
        for (ClienteCelular cliente : clientes) {
            if (cliente.getCelular().getNumero().equals(numero)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Obtiene la lista de clientes.
     * 
     * @return La lista de clientes.
     */
    public List<ClienteCelular> getClientes() {
        return clientes;
    }
}
