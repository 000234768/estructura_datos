package com.mycompany.solemne_1.celular;

/**
 *
 * @author juan
 */
public class ClienteCelular {
    private String rut;
    private String nombre;
    private int edad;
    private char sexo;
    private boolean tienePlan;
    private Celular celular;

    public ClienteCelular(String rut, String nombre, int edad, char sexo, boolean tienePlan, Celular celular) {
        if (edad < 18) {
            throw new IllegalArgumentException("La edad debe ser mayor o igual a 18.");
        }
        this.rut = rut;
        this.nombre = nombre;
        this.edad = edad;
        this.sexo = sexo;
        this.tienePlan = tienePlan;
        this.celular = celular;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public char getSexo() {
        return sexo;
    }

    public boolean isTienePlan() {
        return tienePlan;
    }

    public Celular getCelular() {
        return celular;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "rut='" + rut + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", sexo=" + sexo +
                ", ¿Tiene plan?=" + (tienePlan ? "Sí" : "No") +
                ", celular=" + (celular != null ? celular.toString() : "No tiene celular") +
                '}';
    }
}
