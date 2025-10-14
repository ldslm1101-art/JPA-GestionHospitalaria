package org.example.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Departamento> departamentos = new ArrayList<>();

    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Paciente> pacientes = new ArrayList<>();

    @Builder
    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre del hospital no puede ser nulo.");
        this.direccion = Objects.requireNonNull(direccion, "La dirección del hospital no puede ser nula.");
        this.telefono = Objects.requireNonNull(telefono, "El teléfono del hospital no puede ser nulo.");
    }

    public void agregarDepartamento(Departamento departamento) {
        this.departamentos.add(departamento);
        departamento.setHospital(this);
    }

    public void agregarPaciente(Paciente paciente) {
        this.pacientes.add(paciente);
        paciente.setHospital(this);
    }
}
