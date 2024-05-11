package com.felinus.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idDepto")
public class Departamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDepto;
    private String nombre;
    private String descripcion;

    //    @JsonBackReference
//    @JsonIgnore
    @ManyToMany(mappedBy = "departamentos", cascade = CascadeType.MERGE)
    private Set<Empleado> empleados = new HashSet<>();

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
