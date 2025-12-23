package com.pi.web.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

@Data
public class Cliente {

    private String nome, telefone, cep, endereco;

    @CPF(message = "CPF inválido")
    private String cpf;

    @Email(message = "E-mail inválido")
    private String email;
}
