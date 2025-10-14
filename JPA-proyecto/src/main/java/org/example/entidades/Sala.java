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
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numero;

    private String tipo;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    private Departamento departamento;

    @OneToMany(mappedBy = "sala")
    private List<Cita> citas = new ArrayList<>();

    @Builder
    public Sala(String numero, String tipo, Departamento departamento) {
        this.numero = Objects.requireNonNull(numero, "El número de sala no puede ser nulo.");
        this.tipo = Objects.requireNonNull(tipo, "El tipo de sala no puede ser nulo.");
        this.departamento = Objects.requireNonNull(departamento, "La sala debe pertenecer a un departamento.");
    }
}