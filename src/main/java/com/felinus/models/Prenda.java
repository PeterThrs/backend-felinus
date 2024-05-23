package com.felinus.models;

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

    //@OneToOne(mappedBy = "prenda")
    //private Orden orden;

    private String titulo;
    private String descripcion;
    private int cantidad;
    private double precio;
    private String observaciones;

    @Lob
    private Blob logo;
    @Lob
    private Blob resultado;

}
