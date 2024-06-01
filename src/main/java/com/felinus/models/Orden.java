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
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrden;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE) //muchas ordenes a un cliente
    @JoinColumn(name = "curp_cliente", referencedColumnName = "curp")
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "curp_empleado", referencedColumnName = "curp")
    private Empleado empleado;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_prenda", referencedColumnName = "idPrenda")
    private Prenda prenda;

    private Date fechaInicio;
    private Date fechaEntrega;
    private String etapa;
    private String estado;
    private double total;
    private double anticipo;
}
