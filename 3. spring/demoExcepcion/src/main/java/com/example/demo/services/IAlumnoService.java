package com.example.demo.services;


import com.example.demo.dtos.AlumnoDTO;
import com.example.demo.dtos.response.RespuestaDTO;

import java.util.List;

public interface IAlumnoService {

    List<AlumnoDTO> listarAlumnos();

    AlumnoDTO buscarPorId(Integer id);

    RespuestaDTO agregarAlumno(AlumnoDTO alumnoDTO);

    RespuestaDTO borrarAlumno(Integer id);

    AlumnoDTO actualizarAlumno(Integer id, AlumnoDTO alumnoDTO);

    Boolean existeAlumno(Integer id);
}
