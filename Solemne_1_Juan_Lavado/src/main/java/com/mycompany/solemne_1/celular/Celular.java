package com.mycompany.solemne_1.celular;

/**
 *
 * @author juan
 */
public class Celular {
    private String numero;
    private String marca;

    public Celular(String numero, String marca) {
        setNumero(numero);
        this.marca = marca;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero.length() == 8) {
            this.numero = numero;
        } else {
            throw new IllegalArgumentException("El número de celular debe contener 8 dígitos.");
        }
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    @Override
    public String toString() {
        return numero + " (" + marca + ")";
    }
}
