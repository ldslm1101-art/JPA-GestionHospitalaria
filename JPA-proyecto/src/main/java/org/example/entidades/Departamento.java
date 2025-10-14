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
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Enumerated(EnumType.STRING)
    private EspecialidadMedica especialidad;

    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    @OneToMany(mappedBy = "departamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medico> medicos = new ArrayList<>();

    @OneToMany(mappedBy = "departamento")
    private List<Sala> salas = new ArrayList<>();

    @Builder
    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = Objects.requireNonNull(nombre, "El nombre del departamento no puede ser nulo.");
        this.especialidad = Objects.requireNonNull(especialidad, "La especialidad del departamento no puede ser nula.");
    }

    public void agregarMedico(Medico medico) {
        if (!this.especialidad.equals(medico.getEspecialidad())) {
            throw new IllegalArgumentException("La especialidad del médico no coincide con la del departamento.");
        }
        this.medicos.add(medico);
        medico.setDepartamento(this);
    }

    public void agregarSala(Sala sala) {
        this.salas.add(sala);
        sala.setDepartamento(this);
    }
}
