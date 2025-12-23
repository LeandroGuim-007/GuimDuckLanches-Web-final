package com.pi.web.model;

import lombok.Data;

@Data
public abstract class Produto {
    private String nome, informacaoNutricional, imagem;
    private double valor;
}
