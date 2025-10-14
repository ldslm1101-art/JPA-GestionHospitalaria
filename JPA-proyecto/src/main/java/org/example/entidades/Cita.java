package org.example.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime fechaHora;
    private BigDecimal costo;

    @Enumerated(EnumType.STRING)
    private EstadoCita estado;

    private String observaciones;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "medico_id")
    private Medico medico;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Builder
    public Cita(LocalDateTime fechaHora, BigDecimal costo, EstadoCita estado, String observaciones, Paciente paciente, Medico medico, Sala sala) {
        this.fechaHora = Objects.requireNonNull(fechaHora, "La fecha y hora de la cita no pueden ser nulas.");
        this.costo = Objects.requireNonNull(costo, "El costo de la cita no puede ser nulo.");
        this.estado = Objects.requireNonNull(estado, "El estado de la cita no puede ser nulo.");
        this.observaciones = observaciones;
        this.paciente = Objects.requireNonNull(paciente, "La cita debe tener un paciente asignado.");
        this.medico = Objects.requireNonNull(medico, "La cita debe tener un médico asignado.");
        this.sala = Objects.requireNonNull(sala, "La cita debe tener una sala asignada.");
    }
}