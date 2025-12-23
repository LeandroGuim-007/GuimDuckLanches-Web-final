package com.pi.web.controller;

import com.pi.web.data.ClienteEntity;
import com.pi.web.data.ClienteRepository;
import com.pi.web.data.PedidoEntity;
import com.pi.web.data.ProdutoEntity;
import com.pi.web.data.ProdutoRepository;
import com.pi.web.service.ClienteService;
import com.pi.web.service.PedidoService;
import com.pi.web.service.ProdutoService;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CaixaController {

    @Autowired
    ClienteService clienteService;
    @Autowired
    ProdutoService produtoService;
    @Autowired
    PedidoService pedidoService;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/caixa")
    public String viewCaixaPage(Model model) {
        PedidoEntity pedido = new PedidoEntity();
        pedido.setDataVenda(LocalDate.now());
        model.addAttribute("pedido", pedido);
        model.addAttribute("listaClientes", clienteService.listarTodosClientes());
        model.addAttribute("listaProdutos", produtoService.listarTodosProdutos());
        return "caixa";
    }

    @PostMapping("/cadastrarCompra")
    public String postCaixaPage(@ModelAttribute("pedido") PedidoEntity pedido, @RequestParam Integer clienteID, @RequestParam Integer produtoID) {
        ClienteEntity cliente = clienteRepository.findById(clienteID).orElseThrow();
        ProdutoEntity produto = produtoRepository.findById(produtoID).orElseThrow();

        BigDecimal valorTotal = produto.getValor().multiply(BigDecimal.valueOf(pedido.getQuantidade()));

        pedido.setValor(valorTotal);
        pedido.setCliente(cliente);
        pedido.setProduto(produto);
        pedidoService.criarPedido(pedido);
        return "redirect:/caixa";
    }
}
