package com.felinus.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDepto")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepto;
    private String nombre;
    private String descripcion;


    @ManyToMany(mappedBy = "departamentos")
//    @JsonBackReference
//    @JsonIgnore
    private List<Empleado> empleados;

    // En la clase Departamento
    @Override
    public String toString() {
        return "Departamento{" +
                "idDepto=" + idDepto +
                ", nombre='" + nombre + '\'' +
                ", empleados=" + empleados + // Si empleados es una colección, evita llamar a toString directamente
                '}';
    }

}
