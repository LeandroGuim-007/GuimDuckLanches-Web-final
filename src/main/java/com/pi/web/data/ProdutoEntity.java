package com.pi.web.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "produto")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome, informacaoNutricional;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] imagem;

    private BigDecimal valor;
}
