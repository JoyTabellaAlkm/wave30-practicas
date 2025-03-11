package com.example.demo.services;

import com.example.demo.dtos.AlumnoDTO;
import com.example.demo.dtos.response.RespuestaDTO;
import com.example.demo.exception.NotFoundException;
import com.example.demo.models.Alumno;
import com.example.demo.repository.IAlumnoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService implements IAlumnoService{

    @Autowired
    private IAlumnoRepository alumnoRepository;


    @Autowired
    ObjectMapper objectMapper;

    @Override
    public List<AlumnoDTO> listarAlumnos() {
        List<AlumnoDTO> alumnosList = alumnoRepository.findAll().stream()
                .map( a-> objectMapper.convertValue(a, AlumnoDTO.class))
                .toList();
        return alumnosList;
    }

    public AlumnoDTO buscarPorId(Integer id) {
        Optional<Alumno> alumnoEncontrado =  alumnoRepository.findById(id);
        AlumnoDTO alumnoDTOEncontrado = objectMapper.convertValue(alumnoEncontrado, AlumnoDTO.class);
        return alumnoDTOEncontrado;
    }

    @Override
    public RespuestaDTO agregarAlumno(AlumnoDTO alumnoDTO) {
        Alumno alumno = new Alumno();
        alumno = objectMapper.convertValue(alumnoDTO, Alumno.class);
        alumnoRepository.save(alumno);
        return new RespuestaDTO("Alumno agregado correctamente");
    }

    @Override
    public RespuestaDTO borrarAlumno(Integer id) {
        if (!alumnoRepository.existsById(id)){
            throw new NotFoundException("Registro inexistente");
        }
        alumnoRepository.deleteById(id);
        return new RespuestaDTO("Alumno eliminado correctamente");
    }

    @Override
    public AlumnoDTO actualizarAlumno(Integer id, AlumnoDTO alumnoDTO) {
        if (!alumnoRepository.existsById(id)) {
            throw new RuntimeException("Registro inexistente");
        }
        Alumno alumno = objectMapper.convertValue(alumnoDTO, Alumno.class);
        alumno.setId(id); // Asegúrate de establecer el ID
        Alumno updatedAlumno = alumnoRepository.update(alumno);
        return objectMapper.convertValue(updatedAlumno, AlumnoDTO.class);
    }

    @Override
    public Boolean existeAlumno(Integer id) {
        return alumnoRepository.existsById(id);
    }




}
