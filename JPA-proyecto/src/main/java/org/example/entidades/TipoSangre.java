package org.example.entidades;

import lombok.Getter;

@Getter
public enum TipoSangre {
    A_POSITIVO("A+"),
    A_NEGATIVO("A-"),
    B_POSITIVO("B+"),
    B_NEGATIVO("B-"),
    AB_POSITIVO("AB+"),
    AB_NEGATIVO("AB-"),
    O_POSITIVO("O+"),
    O_NEGATIVO("O-");

    private final String descripcion;

    TipoSangre(String descripcion) {
        this.descripcion = descripcion;
    }

}