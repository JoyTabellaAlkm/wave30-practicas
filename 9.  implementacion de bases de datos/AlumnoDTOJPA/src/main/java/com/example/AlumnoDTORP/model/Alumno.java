package com.example.AlumnoDTORP.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "alumnos")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "nombre", length = 25, nullable = false)
    private String nombre;
    @Column(name = "apellidos", length = 25, nullable = false)
    private String apellidos;
    @Column(name = "fecha_nacimiento", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column(name = "dni", nullable = false, unique = true)
    private Integer dni;
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @OneToOne(mappedBy = "alumno")
    private Usuario usuario;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "alumnos_cursos",
            joinColumns=@JoinColumn(name = "alumnos_id"),
            inverseJoinColumns = @JoinColumn(name = "cursos_id")
    )
    private List<Curso> cursosList;


    //Valor por defecto:
    //
    //    Para relaciones @ManyToOne y @OneToOne, el valor por defecto de FetchType es EAGER.
    //    Para relaciones @OneToMany y @ManyToMany, el valor por defecto de FetchType es LAZY.

}
