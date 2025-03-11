package com.example.demo.repository;

import com.example.demo.models.Alumno;

import java.util.List;
import java.util.Optional;

public interface IAlumnoRepository extends ICrudRepository<Alumno> {

    Boolean existsById(Integer id);
}
