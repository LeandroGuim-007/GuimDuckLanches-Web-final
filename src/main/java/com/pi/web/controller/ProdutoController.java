package com.pi.web.controller;

import com.pi.web.data.BebidaEntity;
import com.pi.web.data.LancheEntity;
import com.pi.web.service.ProdutoService;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping("/cadastroProdutos")
    public String viewCadastroProdutoPage(Model model) {
        model.addAttribute("lanche", new LancheEntity());
        model.addAttribute("bebida", new BebidaEntity());
        return "cadastroProdutos";
    }

    @PostMapping("/adicionarLanche")
    public String PostCadastroLanche(@ModelAttribute("lanche") LancheEntity lanche, BindingResult result, @RequestParam("imagemFile") MultipartFile file)
            throws IOException {

        lanche.setImagem(file.getBytes());
        produtoService.criarProduto(lanche);
        return "redirect:/cadastroProdutos";
    }

    @PostMapping("/adicionarBebida")
    public String PostCadastroBebida(@ModelAttribute("bebida") BebidaEntity bebida, BindingResult result, @RequestParam("imagemFile") MultipartFile file)
            throws IOException {

        bebida.setImagem(file.getBytes());
        produtoService.criarProduto(bebida);
        return "redirect:/cadastroProdutos";
    }

    @GetMapping("/cadastrarProduto/excluir/{id}")
    public String excluirProduto(Model model, @PathVariable Integer id) {
        produtoService.deletarProduto(id);
        return "redirect:/cardapio";
    }
}
