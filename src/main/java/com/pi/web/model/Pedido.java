package com.pi.web.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Pedido {

    private int id;
    private Cliente cliente;
    private Produto produto;
    private int quantidade;
    private LocalDate dataVenda;
    private boolean paraEntregar;
    private String formaPagamento;
    private boolean status;
}
