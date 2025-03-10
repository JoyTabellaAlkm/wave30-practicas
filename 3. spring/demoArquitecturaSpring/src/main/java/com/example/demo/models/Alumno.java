package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    private Integer id;
    private String nombre;
    private String apellidos;
    private Integer edad;
    @JsonProperty("cursos_list")
    private List<Curso> cursosList;
}
