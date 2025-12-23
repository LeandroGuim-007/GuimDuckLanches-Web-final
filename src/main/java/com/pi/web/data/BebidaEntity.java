package com.pi.web.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bebida")
public class BebidaEntity extends ProdutoEntity{
    
    private String marca, sabor;
    private int mls;
    private boolean comGas, comAlcool;
}
