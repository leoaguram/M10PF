package com.dental_flamingos;

import com.dental_flamingos.model.Paciente;
import com.dental_flamingos.repository.PacienteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PacienteFindTest {
    final String ALUMNO = "LEONARDO AGUIRRE RAMIREZ";

    @Autowired
    PacienteRepository pacienteRepository;

    //Consultas derivadas

    @Test
    void buscarTodosTest() {
        System.out.println(ALUMNO);
        System.out.println("Buscar todos los pacientes");
        System.out.println("Existen " + pacienteRepository.count() + " pacientes en la tabla");
        pacienteRepository.findAll().forEach(System.out::println);
    }


}
