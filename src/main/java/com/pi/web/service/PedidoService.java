package com.pi.web.service;

import com.pi.web.data.PedidoRepository;
import com.pi.web.data.PedidoEntity;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public PedidoEntity criarPedido(PedidoEntity pedido) {
        pedido.setId(null);
        pedidoRepository.save(pedido);
        return pedido;
    }

    public PedidoEntity getPedidoId(Integer pedidoId) {

        return pedidoRepository.findById(pedidoId).orElse(null);

    }

    public List<PedidoEntity> listarTodosPedidos() {

        return pedidoRepository.findAll();

    }

    public void deletarPedido(Integer pedidoId) {

        PedidoEntity pedido = getPedidoId(pedidoId);
        pedidoRepository.deleteById(pedido.getId());
    }
    
        public void finalizarPedido(Integer id) {
        PedidoEntity pedido = pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        pedido.setStatus(true);
        pedidoRepository.save(pedido);
    }
}
