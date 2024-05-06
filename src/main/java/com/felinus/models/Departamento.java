package com.felinus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepto;
    private String nombre;
    private String descripcion;

    @JsonIgnore
    @ManyToMany(mappedBy = "departamentos")
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
