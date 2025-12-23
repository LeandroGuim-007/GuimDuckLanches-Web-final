package com.pi.web.service;

import com.pi.web.data.ClienteEntity;
import com.pi.web.data.ClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;
    
     public ClienteEntity criarCliente(ClienteEntity cliente) {
        cliente.setId(null);
        clienteRepository.save(cliente); 
        return cliente;

    }

    public ClienteEntity getClienteId(Integer clienteId) {

        return clienteRepository.findById(clienteId).orElse(null);

    }

    public List<ClienteEntity> listarTodosClientes() {

        return clienteRepository.findAll();

    }

    public void deletarCliente(Integer clienteId) {

        ClienteEntity cliente = getClienteId(clienteId);
        clienteRepository.deleteById(cliente.getId());

    }
}
