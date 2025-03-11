package com.example.demo.repository;

import com.example.demo.models.Curso;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Repository
public class CursoRepositoryImpl implements ICursoRepository{

    private List<Curso> cursosList;

    public CursoRepositoryImpl(){
        System.out.println("Se esta inicializando el repo de Curso");
        this.cursosList = loadData();
    }

    private List<Curso> loadData() {
        List<Curso> loadedData = null;
        File file;

        ObjectMapper objectMapper = new ObjectMapper();

        TypeReference<List<Curso>> typeRef = new TypeReference<>() {};

        try {
            file = ResourceUtils.getFile("classpath:cursos.json");
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

    @Override
    public Curso save(Curso curso) {
        return null;
    }

    @Override
    public Curso update(Curso curso) {
        return null;
    }

    @Override
    public Boolean deleteById(Integer id) {
        return null;
    }

    @Override
    public Optional<Curso> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<Curso> findAll() {
        return List.of();
    }
}
