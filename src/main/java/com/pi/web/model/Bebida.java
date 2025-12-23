package com.pi.web.model;

import lombok.Data;

@Data
public class Bebida extends Produto{
    private String marca, sabor;
    private int mls;
    private boolean comGas, comAlcool;
}
