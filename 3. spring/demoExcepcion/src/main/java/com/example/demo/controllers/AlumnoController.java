package com.example.demo.controllers;

import com.example.demo.dtos.AlumnoDTO;
import com.example.demo.dtos.CursoDTO;
import com.example.demo.dtos.response.RespuestaDTO;
import com.example.demo.models.Alumno;
import com.example.demo.models.Curso;
import com.example.demo.services.IAlumnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private IAlumnoService alumnoService;

    @GetMapping("/listarAlumnos")
    public ResponseEntity<List<AlumnoDTO>> listarAlumnos() {
        return new ResponseEntity<>(alumnoService.listarAlumnos(), HttpStatus.OK);
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<AlumnoDTO> buscarPorId(@PathVariable Integer id){
        return new ResponseEntity<>(alumnoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping("/agregarAlumno")
    public ResponseEntity<RespuestaDTO> agregarAlumno(@RequestBody AlumnoDTO alumnoDTO){
        return new ResponseEntity<>(alumnoService.agregarAlumno(alumnoDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/borrarAlumno/{id}")
    public ResponseEntity<RespuestaDTO> borrarAlumno(@PathVariable Integer id){
        return new ResponseEntity<>(alumnoService.borrarAlumno(id), HttpStatus.OK);
    }

    @PutMapping("/actualizarAlumno/{id}")
    public ResponseEntity<AlumnoDTO> actualizarAlumno(@PathVariable Integer id,@RequestBody AlumnoDTO alumnoDTO){
        return new ResponseEntity<>(alumnoService.actualizarAlumno(id, alumnoDTO), HttpStatus.OK);
    }

    @GetMapping("/existeAlumno/{id}")
    public ResponseEntity<Boolean> existeAlumno(@PathVariable Integer id){
        return new ResponseEntity<>(alumnoService.existeAlumno(id), HttpStatus.OK);
    }

}
