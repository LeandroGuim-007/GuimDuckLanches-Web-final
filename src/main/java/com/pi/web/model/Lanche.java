package com.pi.web.model;

import lombok.Data;

@Data
public class Lanche extends Produto{
    private String ingredientes;
    private int peso;
}
