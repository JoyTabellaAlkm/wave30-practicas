package com.example.demoUnitTest.integrationTest;

import com.example.demoUnitTest.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.text.SimpleDateFormat;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculadoraPromedioControllerIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter writer = objectMapper
            .registerModule(new JavaTimeModule())
            .setDateFormat(new SimpleDateFormat("dd-MM-yyyy"))
            .writer();


    @Test
    @DisplayName("01 - Test Controller : promedio de Alumno OK - Id por PathVariable")
    public void promedioDeAlumnoTestOK() throws Exception {
        //ARRANGE
        Integer idDeEntrada = 1;
        Double resultadoEsperado = 10.0;

        //ACT & ASSERT
        mockMvc.perform(get("/alumno/promedioDeAlumno/{id}", idDeEntrada))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(objectMapper.writeValueAsString(resultadoEsperado))
                ).andDo(print());
    }

    @Test
    @DisplayName("02 - Test Controller : promedio de Alumno FAIL - Id por PathVariable")
    public void promedioDeAlumnoTestFAIL() throws Exception {
        //ARRANGE
        Integer idDeEntrada = 3;
        ExcepcionDTO mensajeExcepcionEsperado = new ExcepcionDTO("Alumno no encontrado");

        //ACT & ASSERT
        mockMvc.perform(get("/alumno/promedioDeAlumno/{id}", idDeEntrada))
                .andExpectAll(
                        status().isNotFound(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(objectMapper.writeValueAsString(mensajeExcepcionEsperado))
                ).andDo(print());
    }

    @Test
    @DisplayName("03 - Test Controller : sumar dos numeros por RequestParam OK")
    public void sumarNumerosTestOK() throws Exception {
        //ARRANGE
        double n1= 1.0;
        double n2 = 1.0;
        Double resultadoEsperado = 2.0;

        //ACT & ASSERT
        mockMvc.perform(get("/alumno/sumar").param("n1", Double.toString(n1)).param("n2", Double.toString(n2)))
                .andExpectAll(
                        status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(objectMapper.writeValueAsString(resultadoEsperado))
                ).andDo(print());
    }

    @Test
    @DisplayName("04 - Test Controller : agregar un alumnoDTO OK")
    public void addAlumnoTestOK() throws Exception {
        //ARRANGE
        AlumnoDTO alumnoDto = new AlumnoDTO(1, "Johanna", "Tabella",
                                List.of(new CursoDTO(101, "Matemática", 8.0))
                                );

        ResponseDTO responseEsperada = new ResponseDTO("Alumno guardado");

        //ACT & ASSERT
        mockMvc.perform(post("/alumno/addAlumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoDto)))
                .andExpectAll(
                        status().isCreated(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(objectMapper.writeValueAsString(responseEsperada))
                ).andDo(print());
    }

    @Test
    @DisplayName("05 - Test Controller : agregar un alumnoDTO Validacion FAIL - Nombre vacio")
    public void addAlumnoTestValidacionFail() throws Exception {
        //ARRANGE
        AlumnoDTO alumnoDto = new AlumnoDTO(1, "", "Tabella",
                                List.of(new CursoDTO(101, "Matemática", 8.0))
                                );
        ErrorDTO errorEsperado = new ErrorDTO("Se encontraron los siguientes errores en las validaciones: @Valid del DTO",
                List.of("El nombre del alumno debe tener entre 3 a 10 caracteres",
                        "El nombre del estudiante debe comenzar con mayúscula."));

        //ACT & ASSERT
        mockMvc.perform(post("/alumno/addAlumno")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(alumnoDto)))
                .andExpectAll(
                        status().isBadRequest(),
                        content().contentType(MediaType.APPLICATION_JSON),
                        content().json(objectMapper.writeValueAsString(errorEsperado))
                ).andDo(print());
    }


}
