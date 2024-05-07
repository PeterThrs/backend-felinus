package com.felinus.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "idUsuario")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
public class Empleado extends Usuario{

//    @OneToOne
//    @JoinColumn(name = "idUsuario")
//    private Usuario user;

    private String usuario;
    private String password;
    private LocalDate fechaAlta;
    private Boolean activo;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "DeptoEmpleado",
            joinColumns = @JoinColumn(name = "idEmpleado"),
            inverseJoinColumns = @JoinColumn(name = "idDepto"))
//    @JsonManagedReference //referencia padre
    private List<Departamento> departamentos;

    @Override
    public String toString() {
        return "Empleado{" +
                "usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", fechaAlta=" + fechaAlta +
                ", activo=" + activo +
                '}';
    }


}
