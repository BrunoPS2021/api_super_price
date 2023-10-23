package com.app.restApiAndroid.repositories;

import com.app.restApiAndroid.models.preco.Preco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrecoRepository extends JpaRepository<Preco,String> {
    List<Preco> findAllByUsuarioId(String userId);
    List<Preco> findAllByProdutoId(String productId);
    List<Preco> findAllByEmpresaId(String businessId);

}
