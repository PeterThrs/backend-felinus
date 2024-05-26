package com.felinus.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.felinus.serialization.BlobDeserializer;
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
    @JsonDeserialize(using = BlobDeserializer.class)
    private Blob logo;
    @Lob
    private Blob resultado;

}
