package com.example.demo.repository;

import com.example.demo.models.Alumno;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class AlumnoRepositoryImpl implements IAlumnoRepository {


    private List<Alumno> alumnosList;

    public AlumnoRepositoryImpl(){
        System.out.println("Se esta inicializando el Repo Alumno");
        this.alumnosList = loadData();
    }


    public List<Alumno> findAll() {
        return alumnosList;
    }


    public Alumno findById(Integer id) {
        Alumno alumno = alumnosList.stream()
                .filter(a -> a.getId().equals(id))
                .findFirst()
                .orElse(null);

        return alumno;
    }


    public Alumno save(Alumno alumno) {
        alumno.setId(alumnosList.size() + 1);
        alumnosList.add(alumno);
        return alumno;
    }


    public Boolean deleteById(Integer id) {
        Alumno alumnoEncontrado = findById(id);
        alumnosList.remove(alumnoEncontrado);
        return true;
    }


    public Alumno update(Alumno alumno) {
        Alumno alumnoEncontrado = findById(alumno.getId());
        alumno.setId(alumnoEncontrado.getId());


        alumnosList.remove(alumnoEncontrado);
        alumnosList.add(alumno);

        return alumno;
    }


    public Boolean existsById(Integer id) {
        Alumno alumno = this.findById(id);
        return alumno != null;
    }




    private List<Alumno> loadData() {
        List<Alumno> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper()
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .registerModule(new JavaTimeModule());

        TypeReference<List<Alumno>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:users.json");
            loadedData = objectMapper.readValue(file, typeRef);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your resources files");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed while initializing DB, check your JSON formatting.");
        }

        return loadedData;
    }

}
