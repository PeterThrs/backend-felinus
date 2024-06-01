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
@PrimaryKeyJoinColumn(name = "curp")
public class Empleado extends Usuario{

    private String usuario;
    private String password;
    private Date fechaAlta;
    private Boolean activo;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "nombre", referencedColumnName = "nombre")
    private Departamento departamento;

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
