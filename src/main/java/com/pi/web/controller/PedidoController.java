package com.pi.web.controller;

import com.pi.web.data.ClienteEntity;
import com.pi.web.data.PedidoEntity;
import com.pi.web.service.ClienteService;
import com.pi.web.service.PedidoService;
import com.pi.web.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class PedidoController {

    @Autowired
    PedidoService pedidoService;
    @Autowired
    ClienteService clienteService;

    @GetMapping("/pedidos")
    public String viewPedidosPage(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodosPedidos());
        return "pedidos";
    }

    @GetMapping("/pedidos/excluir/{id}")
    public String deletePedidos(Model model, @PathVariable Integer id) {
        pedidoService.deletarPedido(id);
        return "redirect:/pedidos";
    }

    @GetMapping("/pedidos/detalhes/{id}")
    public String detalhesPedido(@PathVariable Integer id, Model model) {

        ClienteEntity cliente = clienteService.getClienteId(id);

        model.addAttribute("cliente", cliente);
        model.addAttribute("somenteLeitura", true);

        return "cadastroCliente";
    }

    @GetMapping("/pedidos/finalizar/{id}")
    public String finalizarPedido(@PathVariable Integer id) {
        pedidoService.finalizarPedido(id);
        return "redirect:/pedidos";
    }
}
