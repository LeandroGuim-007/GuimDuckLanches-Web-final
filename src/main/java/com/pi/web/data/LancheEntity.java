package com.pi.web.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "lanche")
public class LancheEntity extends ProdutoEntity{
    
    private String ingredientes;
    private int peso;
}
