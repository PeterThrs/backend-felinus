package com.felinus.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@PrimaryKeyJoinColumn(name = "curp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends Usuario{

    private String direccionEntrega;

}
