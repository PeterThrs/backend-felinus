package com.felinus.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "idUsuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Integer idCliente;
    private Integer totalCompras;
    private String direccionEntrega;

//    @OneToOne
//    @JoinColumn(name = "idUsuario")
//    private Usuario user;
}
