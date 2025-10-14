package org.example.entidades;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter

@NoArgsConstructor
public class HistoriaClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroHistoria;
    private LocalDateTime fechaCreacion;

    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    @ElementCollection
    private List<String> diagnosticos = new ArrayList<>();

    @ElementCollection
    private List<String> tratamientos = new ArrayList<>();

    @ElementCollection
    private List<String> alergias = new ArrayList<>();

    public HistoriaClinica(Paciente paciente) {
        this.paciente = paciente;
        this.fechaCreacion = LocalDateTime.now();
        this.numeroHistoria = generarNumeroHistoria(paciente.getDni());
    }

    private String generarNumeroHistoria(String dni) {
        return "HC-" + dni + "-" + LocalDateTime.now().getNano();
    }
}