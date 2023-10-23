package com.app.restApiAndroid.repositories;

import com.app.restApiAndroid.models.produto.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto,String> {
    Produto findByNome(String nome);
    List<Produto> findAllByNome(String nome);
    List<Produto> findAllByNomeContaining(String nome);
    List<Produto> findAllByGtin(String gtin);
    Produto findByGtin(String gtin);
    List<Produto> findAllByTipoProdutoContaining(String TipoProduto);
}
