package com.felinus.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empleado extends Usuario{

//    @OneToOne
//    @JoinColumn(name = "idUsuario")
//    private Usuario user;

    private String usuario;
    private String password;
    private LocalDate fechaAlta;
    private Boolean activo;

    @ManyToMany
    @JoinTable(name = "DeptoEmpleado",
            joinColumns = @JoinColumn(name = "idEmpleado"),
            inverseJoinColumns = @JoinColumn(name = "idDepto"))
    private Set<Departamento> departamentos = new HashSet<>();
}
