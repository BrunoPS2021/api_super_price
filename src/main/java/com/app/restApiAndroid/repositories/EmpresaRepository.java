package com.app.restApiAndroid.repositories;

import com.app.restApiAndroid.models.empresa.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmpresaRepository extends JpaRepository<Empresa,String> {
    boolean existsByNome(String nome);
    Empresa findByNome(String nome);
    List<Empresa> findAllByNome(String nome);
    List<Empresa> findAllByNomeContaining(String nome);
    boolean existsByCnpj(String cnpj);
    List<Empresa> findAllByCnpj(String cnpj);
    Empresa findByCnpj(String cnpj);
    boolean existsByFantasia(String fantasia);
    Empresa findByFantasia(String fantasia);
    List<Empresa> findAllByFantasia(String fantasia);
    List<Empresa> findAllByFantasiaContaining(String fantasia);
    List<Empresa> findAllByUfContaining(String uf);
    List<Empresa> findAllByCepContaining(String cep);
    List<Empresa> findAllByLogradouroContaining(String logradouro);
    List<Empresa> findAllByBairroContaining(String bairro);
    List<Empresa> findAllByMunicipioContaining(String municipio);
}
