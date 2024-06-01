package com.felinus.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventario {

    @Id
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidadMedida;
    private double cantidad;
}
