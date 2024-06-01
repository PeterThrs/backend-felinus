package com.felinus.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimientoInventario {
    @Id
    private String clave;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "codigo", referencedColumnName = "codigo")
    private Inventario inventario;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "curp_empleado", referencedColumnName = "curp")
    private Empleado empleado;
    private Date fechaMov;
    private String tipoMov;
    private Double cantidadMov;
    private String razon;
}
