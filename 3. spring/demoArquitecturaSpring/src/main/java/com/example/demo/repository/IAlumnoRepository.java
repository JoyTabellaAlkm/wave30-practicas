package com.example.demo.repository;

import com.example.demo.models.Alumno;

import java.util.List;

public interface IAlumnoRepository {

    List<Alumno> findAll();

    Alumno findById(Integer id);

    Alumno save(Alumno alumno);

    Boolean deleteById(Integer id);

    Alumno update(Alumno alumno);

    Boolean existsById(Integer id);
}
