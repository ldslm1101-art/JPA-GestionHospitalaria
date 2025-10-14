package org.example.entidades;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Paciente extends Persona {

    private String telefono;
    private String direccion;

    // Relación OneToOne con HistoriaClinica, con cascading para que se guarde automáticamente
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private HistoriaClinica historiaClinica;

    // Relación ManyToOne con Hospital
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    // Relación OneToMany con Cita
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Cita> citas;

    // Constructor protegido para que el @SuperBuilder de Lombok sepa cómo inicializar la clase
    // Aquí se inicializa la lista de citas y se crea la historia clínica automáticamente
    protected Paciente(PacienteBuilder<?, ?> builder) {
        super(builder);
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
        this.citas = new ArrayList<>();
        validarDni(dni);

        // La historia clínica se crea automáticamente cuando se crea el paciente
        this.historiaClinica = new HistoriaClinica(this);
    }
}