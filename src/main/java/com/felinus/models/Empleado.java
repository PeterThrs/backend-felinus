package com.felinus.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "idUsuario")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idUsuario")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Empleado extends Usuario{

    private String usuario;
    private String password;
    private Date fechaAlta;
    private Boolean activo;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "DeptoEmpleado",
            joinColumns = @JoinColumn(name = "idEmpleado"),
            inverseJoinColumns = @JoinColumn(name = "idDepto"))
//    @JsonManagedReference //referencia padre
    Set<Departamento> departamentos = new HashSet<>();

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
