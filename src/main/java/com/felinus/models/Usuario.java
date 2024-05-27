package com.felinus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_usuario")
    private Integer idUsuario;
    private String nombre;
    private String apPaterno;
    private String apMaterno;
    private String domicilioActual;
    private String telefono;
    private String email;

    @Lob
    @JsonIgnore
    private Blob imgUsuario;

}
