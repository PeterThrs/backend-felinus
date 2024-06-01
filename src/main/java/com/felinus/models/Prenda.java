package com.felinus.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPrenda;

    private String titulo;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String observaciones;
    private Boolean entregado;

    @Lob
    @JsonIgnore
    private Blob logo;
    @JsonIgnore
    @Lob
    private Blob resultado;

}
