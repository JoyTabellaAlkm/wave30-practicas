package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {

    private String nombre;
    private String apellido;
    private Integer edad;
    private List<Curso> cursosList;
}
