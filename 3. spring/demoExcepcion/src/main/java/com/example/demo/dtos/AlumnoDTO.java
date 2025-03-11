package com.example.demo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlumnoDTO{
    private String nombre;
    private String apellidos;
    @JsonProperty("cursos_list")
    private List<CursoDTO> cursosList;
}
