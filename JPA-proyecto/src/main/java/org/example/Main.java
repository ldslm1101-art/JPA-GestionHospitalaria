package org.example;

import org.example.entidades.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Iniciando aplicación de gestión hospitalaria...");

        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("hospital-persistence-unit");
            em = emf.createEntityManager();

            // INICIA LA ÚNICA TRANSACCIÓN DEL PROGRAMA
            em.getTransaction().begin();

            // 1. Crear un Hospital
            Hospital hospital = Hospital.builder()
                    .nombre("Hospital Central UTN")
                    .direccion("Av. 9 de Julio 123, CABA")
                    .telefono("011-4567-8910")
                    .build();

            // 2. Crear Departamentos y Salas
            Departamento cardiologia = Departamento.builder()
                    .nombre("Cardiología")
                    .especialidad(EspecialidadMedica.CARDIOLOGIA)
                    .build();
            hospital.agregarDepartamento(cardiologia);

            Departamento pediatria = Departamento.builder()
                    .nombre("Pediatría")
                    .especialidad(EspecialidadMedica.PEDIATRIA)
                    .build();
            hospital.agregarDepartamento(pediatria);

            Sala sala101 = Sala.builder().numero("101").tipo("Consultorio").departamento(cardiologia).build();
            Sala sala202 = Sala.builder().numero("202").tipo("Consultorio").departamento(pediatria).build();

            // 3. Crear Médicos (con Lombok @SuperBuilder)
            Medico medicoCardiologo = Medico.builder()
                    .nombre("Juan")
                    .apellido("Pérez")
                    .dni("25123456")
                    .fechaNacimiento(LocalDateTime.of(1980, 5, 10, 0, 0).toLocalDate())
                    .especialidad(EspecialidadMedica.CARDIOLOGIA)
                    .matricula(new Matricula("MP-12345"))
                    .build();
            cardiologia.agregarMedico(medicoCardiologo);

            Medico medicoPediatra = Medico.builder()
                    .nombre("Ana")
                    .apellido("Gómez")
                    .dni("30987654")
                    .fechaNacimiento(LocalDateTime.of(1990, 8, 20, 0, 0).toLocalDate())
                    .especialidad(EspecialidadMedica.PEDIATRIA)
                    .matricula(new Matricula("MP-67890"))
                    .build();
            pediatria.agregarMedico(medicoPediatra);

            // 4. Crear Pacientes (con Lombok @SuperBuilder)
            Paciente paciente1 = Paciente.builder()
                    .nombre("Carlos")
                    .apellido("López")
                    .dni("40001002")
                    .fechaNacimiento(LocalDateTime.of(2000, 1, 15, 0, 0).toLocalDate())
                    .telefono("11-1234-5678")
                    .direccion("Calle Falsa 123")
                    .build();
            hospital.agregarPaciente(paciente1);

            Paciente paciente2 = Paciente.builder()
                    .nombre("Marta")
                    .apellido("Sánchez")
                    .dni("35003004")
                    .fechaNacimiento(LocalDateTime.of(1995, 3, 25, 0, 0).toLocalDate())
                    .telefono("11-9876-5432")
                    .direccion("Av. Siempre Viva 742")
                    .build();
            hospital.agregarPaciente(paciente2);

            // 5. Persistir el Hospital (con cascading)
            em.persist(hospital);
            System.out.println("Datos iniciales guardados correctamente.");

            // --- Consultas y Operaciones (CRÍTICO para la nota) ---
            // Las operaciones siguientes no necesitan una nueva transacción.

            // Recuperar un médico por especialidad (Consulta JPQL con TypedQuery)
            TypedQuery<Medico> medicoQuery = em.createQuery(
                    "SELECT m FROM Medico m WHERE m.especialidad = :esp", Medico.class);
            medicoQuery.setParameter("esp", EspecialidadMedica.CARDIOLOGIA);
            List<Medico> medicosEncontrados = medicoQuery.getResultList();

            if (!medicosEncontrados.isEmpty()) {
                Medico cardiologoEncontrado = medicosEncontrados.get(0);
                System.out.println("\nMedico encontrado por especialidad: " + cardiologoEncontrado.getNombreCompleto());
            }

            // Recuperar todos los pacientes
            TypedQuery<Paciente> pacienteQuery = em.createQuery(
                    "SELECT p FROM Paciente p", Paciente.class);
            List<Paciente> pacientes = pacienteQuery.getResultList();
            System.out.println("\nPacientes en el sistema:");
            pacientes.forEach(p -> System.out.println("- " + p.getNombreCompleto()));

            // Actualizar un paciente
            Paciente pacienteParaActualizar = em.find(Paciente.class, paciente1.getId());
            pacienteParaActualizar.setTelefono("11-9999-9999");
            em.merge(pacienteParaActualizar);
            System.out.println("\nTeléfono del paciente " + pacienteParaActualizar.getNombreCompleto() + " actualizado a " + pacienteParaActualizar.getTelefono());

            // Crear y persistir citas
            Cita cita1 = Cita.builder()
                    .fechaHora(LocalDateTime.now().plusDays(5))
                    .costo(new BigDecimal("150.00"))
                    .estado(EstadoCita.PROGRAMADA)
                    .observaciones("Primera revisión")
                    .paciente(paciente1)
                    .medico(medicoCardiologo)
                    .sala(sala101)
                    .build();
            em.persist(cita1);

            Cita cita2 = Cita.builder()
                    .fechaHora(LocalDateTime.now().plusDays(10))
                    .costo(new BigDecimal("100.00"))
                    .estado(EstadoCita.PROGRAMADA)
                    .observaciones("Control de rutina")
                    .paciente(paciente2)
                    .medico(medicoPediatra)
                    .sala(sala202)
                    .build();
            em.persist(cita2);

            // Contar el total de pacientes
            TypedQuery<Long> countQuery = em.createQuery(
                    "SELECT COUNT(p) FROM Paciente p", Long.class);
            Long totalPacientes = countQuery.getSingleResult();
            System.out.println("\nTotal de pacientes en el sistema: " + totalPacientes);

            // HACE EL COMMIT DE TODAS LAS OPERACIONES
            em.getTransaction().commit();

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Ocurrió un error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
        System.out.println("\nSISTEMA EJECUTADO EXITOSAMENTE");
    }
}