package com.pi.web.controller;

import com.pi.web.data.ClienteEntity;
import com.pi.web.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    @GetMapping("/cadastroCliente")
    public String viewCadastroClientePage(Model model) {
        model.addAttribute("cliente", new ClienteEntity());
        model.addAttribute("somenteLeitura", false);

        return "cadastroCliente";
    }

    @PostMapping("/adicionarCliente")
    public String postCadastrarCliente(@Valid @ModelAttribute("cliente") ClienteEntity cliente, BindingResult result) {
        clienteService.criarCliente(cliente);
        return "redirect:/cadastroCliente";
    }

}
