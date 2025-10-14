package org.example.entidades;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class Matricula implements Serializable {

    private String numero;

    public Matricula() {}

    public Matricula(String numero) {
        validarFormato(numero);
        this.numero = numero;
    }
    private void validarFormato(String numero) {
        if (!numero.matches("MP-\\d{4,6}")) {
            throw new IllegalArgumentException("Formato de matrícula inválido");
        }
    }
    public String getNumero() {
        return numero;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matricula matricula = (Matricula) o;
        return Objects.equals(numero, matricula.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public String toString() {
        return numero;
    }
}