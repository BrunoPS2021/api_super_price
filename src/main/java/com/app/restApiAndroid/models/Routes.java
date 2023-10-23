package com.app.restApiAndroid.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "route")
@Getter
@Setter
@NoArgsConstructor
public class Routes {
    private String principal;

    private String cadastroUsuario;
    private String atualizaUsuarioToId;
    private String atualizaUsuarioToLogin;
    private String deletaUsuarioToId;
    private String deletaUsuarioToLogin;
    private String buscaListaUsuarios;
    private String buscaListaUsuarioToNome;
    private String buscaUsuarioToNome;
    private String buscaUsuarioToId;
    private String buscaUsuarioToLogin;
    private String buscaUsuarioToEmail;
    private String buscaUsuarioToLoginEmail;
    private String autenticacaoLogin;

    private String cadastroProduto;
    private String atualizaProdutoToId;
    private String atualizaProdutoToGtin;
    private String deletaProdutoToId;
    private String deletaProdutoToGtin;
    private String buscaListaProdutos;
    private String buscaListaProdutosToTipo;
    private String buscaListaProdutoToNome;
    private String buscaProdutoToNome;
    private String buscaProdutoToId;
    private String buscaProdutoToGtin;
    private String buscaProdutoApiExterna;

    private String cadastroEmpresa;
    private String atualizaEmpresaToId;
    private String atualizaEmpresaToCnpj;
    private String atualizaEmpresaToNome;
    private String atualizaEmpresaToFantasia;
    private String deletaEmpresaToId;
    private String deletaEmpresaToCnpj;
    private String deletaEmpresaToNome;
    private String deletaEmpresaToFantasia;
    private String buscaListaEmpresas;
    private String buscaEmpresaToId;
    private String buscaEmpresaToCnpj;
    private String buscaEmpresaToNome;
    private String buscaListaEmpresaToNome;
    private String buscaEmpresaToFantasia;
    private String buscaListaEmpresaToFantasia;
    private String buscaListaEmpresaToUf;
    private String buscaListaEmpresaToMunicipio;
    private String buscaListaEmpresaToUfMunicipio;
    private String buscaEmpresaApiExterna;

    private String cadastroPreco;
    private String atualizaPrecoToId;
    private String atualizaPrecoToIdProEmp;
    private String atualizaPrecoToIdPreProEmp;
    private String deletaPrecoToId;
    private String deletaPrecoToIdPro;
    private String deletaPrecoToIdEmp;
    private String deletaPrecoToIdProEmp;
    private String deletaPrecoToIdPreProEmp;
    private String deletaPrecoToIdPrePro;
    private String deletaPrecoToIdPreEmp;
    private String buscaListaPrecos;
    private String buscaPrecoToId;
    private String buscaListaPrecoToIdPro;
    private String buscaListaPrecoToIdEmp;
    private String buscaPrecoToIdPrePro;
    private String buscaPrecoToIdPreEmp;
    private String buscaListaPrecoToIdProEmp;
    private String buscaPrecoToIdPreProEmp;

}
