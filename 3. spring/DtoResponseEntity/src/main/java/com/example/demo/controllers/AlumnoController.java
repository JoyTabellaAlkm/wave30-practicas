package com.example.demo.controllers;

import com.example.demo.dtos.AlumnoDTO;
import com.example.demo.dtos.CursoDTO;
import com.example.demo.models.Alumno;
import com.example.demo.models.Curso;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {
    Curso curso1 = new Curso(1, "Matematicas");
    Curso curso2 = new Curso(2, "Fisica");

    Alumno alumno = new Alumno("Juan", "Perez",22, List.of(curso1, curso2));


    @GetMapping("/traerAlumno")
    public ResponseEntity<AlumnoDTO> traerAlumno() {
        CursoDTO cursoDTO = new CursoDTO();
        cursoDTO.setId(curso1.getId());
        cursoDTO.setNombre(curso1.getNombre());

        CursoDTO cursoDTO2 = new CursoDTO();
        cursoDTO2.setId(curso2.getId());
        cursoDTO2.setNombre(curso2.getNombre());

        List<CursoDTO> cursosList = List.of(cursoDTO, cursoDTO2);



        AlumnoDTO alumnoDTO = new AlumnoDTO();
        alumnoDTO.setNombre(alumno.getNombre());
        alumnoDTO.setApellido(alumno.getApellido());
        alumnoDTO.setCursosList(cursosList);

        // De esta manera tambien esta bien hacerlo :D
        //return new ResponseEntity<>(alumnoDTO, HttpStatus.OK);
        return ResponseEntity.ok(alumnoDTO);
    }

}
