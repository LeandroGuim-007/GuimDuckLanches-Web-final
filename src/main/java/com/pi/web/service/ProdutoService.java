package com.pi.web.service;

import com.pi.web.data.ProdutoEntity;
import com.pi.web.data.ProdutoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public ProdutoEntity criarProduto(ProdutoEntity produto) {
        produto.setId(null);
        produtoRepository.save(produto); 
        return produto;
    }

    public ProdutoEntity getProdutoId(Integer produtoId) {

        return produtoRepository.findById(produtoId).orElse(null);

    }

    public List<ProdutoEntity> listarTodosProdutos() {

        return produtoRepository.findAll();

    }

    public void deletarProduto(Integer produtoId) {

        ProdutoEntity produto = getProdutoId(produtoId);
        produtoRepository.deleteById(produto.getId());
    }
}
