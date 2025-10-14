package org.example.entidades;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Setter
@SuperBuilder
public class Medico extends Persona {

    @Embedded
    private Matricula matricula;

    @Enumerated(EnumType.STRING)
    private EspecialidadMedica especialidad;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "medico", cascade = CascadeType.ALL)
    private List<Cita> citas;

    // Constructor protegido para inicializar la lista de citas (CRÍTICO)
    protected Medico(MedicoBuilder<?, ?> builder) {
        super(builder);
        this.matricula = builder.matricula;
        this.especialidad = builder.especialidad;
        this.departamento = builder.departamento;
        this.citas = new ArrayList<>();
        validarDni(dni);
    }
}
