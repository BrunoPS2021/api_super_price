package com.app.restApiAndroid.services.servicesgets;

import com.app.restApiAndroid.models.Mensagem;
import com.app.restApiAndroid.models.apisExternas.empresa.EmpresaApiExterna;
import com.app.restApiAndroid.models.apisExternas.produto.ProdutoApiExterna;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.preco.Preco;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.repositories.*;
import com.app.restApiAndroid.services.servicesvalidates.apiExterna.EmpresasApiExternaService;
import com.app.restApiAndroid.services.servicesvalidates.apiExterna.ProdutosApiExternaService;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Service
public class BuscasService {

    //usuario
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private DadosUsuarioRepository dadosUsuarioRepository;

    public ResponseEntity listUser() {
        Mensagem mensagem = new Mensagem();
        var listUsers = dadosUsuarioRepository.findAll();

        if (listUsers.isEmpty()) {
            mensagem.setMessage("Lista usuários vazia!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }

    public ResponseEntity listUserToName(String name) {

        Mensagem mensagem = new Mensagem();

        if (name.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var user = dadosUsuarioRepository.findAllByNameContaining(removerAcentosEspacoBranco(
                name.toUpperCase()));

        if (user.isEmpty()) {
            mensagem.setMessage(String.format("O usuário %s não existe!", name.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity userToName(String name) {

        Mensagem mensagem = new Mensagem();

        if (name.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var user = dadosUsuarioRepository.findByName(removerAcentosEspacoBranco(name.toUpperCase()));

        if (user == null) {
            mensagem.setMessage(String.format("O usuário %s não existe!", name.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity userToId(String id) {

        Mensagem mensagem = new Mensagem();

        if (id.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var user = dadosUsuarioRepository.findAllByUserId(id);

        if (user.isEmpty()) {
            mensagem.setMessage(String.format("O usuário com id %s não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity userToLogin(String login) {
        Mensagem mensagem = new Mensagem();

        if (login.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Login não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var user = dadosUsuarioRepository.findAllByUserLogin(removerAcentosEspacoBranco(
                login.toUpperCase()));

        if (user.isEmpty()) {
            mensagem.setMessage(String.format("O Login %s não existe!", login.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity userToEmail(String email) {
        Mensagem mensagem = new Mensagem();

        if (email.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Email não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var user = dadosUsuarioRepository.findByEmail(removerAcentosEspacoBranco(
                email.toUpperCase()));

        if (user == null) {
            mensagem.setMessage(String.format("O email %s não existe!", email.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public ResponseEntity userToLoginEmail(String loginEmail) {
        Mensagem mensagem = new Mensagem();

        if (loginEmail.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Login ou email não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var user = dadosUsuarioRepository.findByEmail(removerAcentosEspacoBranco(
                loginEmail.toUpperCase()));

        if (user == null) {
            user = dadosUsuarioRepository.findByUserLogin(removerAcentosEspacoBranco(
                    loginEmail.toUpperCase()));

            if (user == null) {
                mensagem.setMessage(String.format("O login ou email %s não existe!", loginEmail.toUpperCase()));
                return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
            }
        }


        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //produto
    @Autowired
    private ProdutoRepository produtoRepository;

    public ResponseEntity listaProdutos() {

        Mensagem mensagem = new Mensagem();

        var listaProduto = produtoRepository.findAll();

        if (listaProduto.isEmpty()) {
            mensagem.setMessage("Lista produto vazia!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaProduto, HttpStatus.OK);
    }

    public ResponseEntity listaProdutosToTipo(String tipo) {

        Mensagem mensagem = new Mensagem();

        if (tipo.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Tipo não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var listaProdutoToTipo = produtoRepository.findAllByTipoProdutoContaining(
                removerAcentosEspacoBranco(tipo.toUpperCase()));

        if (listaProdutoToTipo.isEmpty()) {
            mensagem.setMessage(String.format("O produtos do %s não existe!", tipo.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaProdutoToTipo, HttpStatus.OK);
    }

    public ResponseEntity produtoToNome(String nome) {

        Mensagem mensagem = new Mensagem();

        if (nome.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var produtoToNome = produtoRepository.findByNome(removerAcentosEspacoBranco(
                nome.toUpperCase()));

        if (produtoToNome == null) {
            mensagem.setMessage(String.format("O produtos do %s não existe!", nome.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produtoToNome, HttpStatus.OK);
    }

    public ResponseEntity listaProdutoToNome(String nome) {

        Mensagem mensagem = new Mensagem();

        if (nome.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var produto = produtoRepository.findAllByNomeContaining(removerAcentosEspacoBranco(
                nome.toUpperCase()));

        if (produto.isEmpty()) {
            mensagem.setMessage(String.format("O produto %s não existe!", nome.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    public ResponseEntity produtoToId(String id) {

        Mensagem mensagem = new Mensagem();

        if (id.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var produto = produtoRepository.findById(id);

        if (produto.isEmpty()){
            mensagem.setMessage(String.format("O produto com id %s não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    public ResponseEntity produtoToGtin(String gtin) {

        Mensagem mensagem = new Mensagem();

        if (gtin.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Código de barras não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var produto = produtoRepository.findByGtin(gtin.toUpperCase());

        if (produto == null){
            mensagem.setMessage(String.format("O produto com código de barras %s não existe!", gtin.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    //produtoApiExterna
    @Autowired
    private ProdutosApiExternaService produtosApiExternaService;

    public ResponseEntity buscaProdutoApiExterna(String gtin) {

        Mensagem mensagem = new Mensagem();

        if (gtin.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("O código de barras esta vázio"));
            return new ResponseEntity<>(
                    mensagem,
                    HttpStatus.NOT_ACCEPTABLE);
        }
        try {
            ProdutoApiExterna produtoSalvar = new ProdutoApiExterna();

            Triplet<RestTemplate, UriComponents, HttpEntity> resultProdutoApiExtrena =
                    produtosApiExternaService.resultQueryProdutoApiExterna("gtins/%s",
                            removerAcentosEspacoBranco(gtin));
            ResponseEntity<ProdutoApiExterna> produtoApiExternaResponse =
                    resultProdutoApiExtrena.getValue0().exchange(
                            resultProdutoApiExtrena.getValue1().toUriString(),
                            HttpMethod.GET,
                            resultProdutoApiExtrena.getValue2(),
                            ProdutoApiExterna.class);

            produtoSalvar = produtoApiExternaResponse.getBody();

            Produto produtoNew = produtosApiExternaService.produto(produtoSalvar);

            return new ResponseEntity<>(produtoNew, HttpStatus.OK);

        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            mensagem.setMessage(String.format("Api externa de Consulta de produto: %s", httpClientOrServerExc.getMessage()));
            return new ResponseEntity<>(
                    mensagem,
                    httpClientOrServerExc.getStatusCode());
        }
    }

    //emprasa
    @Autowired
    private EmpresaRepository empresaRepository;

    public ResponseEntity listaEmpresas() {

        Mensagem mensagem = new Mensagem();

        var listaEmpresa = empresaRepository.findAll();

        if (listaEmpresa.isEmpty()) {
            mensagem.setMessage("Lista empresa vazia!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaEmpresa, HttpStatus.OK);
    }

    public ResponseEntity empresaToId(String id) {

        Mensagem mensagem = new Mensagem();

        if (id.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var empresa = empresaRepository.findById(id);

        if (empresa.isEmpty()){
            mensagem.setMessage(String.format("A empresa com id %s não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
    }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity empresaToCnpj(String cnpj) {

        Mensagem mensagem = new Mensagem();

        if (cnpj.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Cnpj não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var empresa = empresaRepository.findByCnpj(cnpj);

        if (empresa == null) {
            mensagem.setMessage(String.format("A empresa com cnpj %s não existe!", cnpj.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity empresaToNome(String nome) {

        Mensagem mensagem = new Mensagem();

        if (nome.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var empresa = empresaRepository.findByNome(removerAcentosEspacoBranco(nome.toUpperCase()));

        if (empresa == null) {
            mensagem.setMessage(String.format("A empresa %s não existe!", nome.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity empresaToFantasia(String fantasia) {

        Mensagem mensagem = new Mensagem();

        if (fantasia.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome fantasia não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var empresa = empresaRepository.findByFantasia(removerAcentosEspacoBranco(fantasia.toUpperCase()));

        if (empresa == null) {
            mensagem.setMessage(String.format("A empresa com nome fantasia %s não existe!", fantasia.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity listaEmpresaToNome(String nome) {

        Mensagem mensagem = new Mensagem();

        if (nome.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var empresa = empresaRepository.findAllByNomeContaining(removerAcentosEspacoBranco(
                nome.toUpperCase()));

        if (empresa.isEmpty()) {
            mensagem.setMessage(String.format("A empresa %s não existe!", nome.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity listaEmpresaToFantasia(String fantasia) {

        Mensagem mensagem = new Mensagem();

        if (fantasia.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Nome fantasia não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var empresa = empresaRepository.findAllByFantasiaContaining(removerAcentosEspacoBranco(
                fantasia.toUpperCase()));

        if (empresa.isEmpty()) {
            mensagem.setMessage(String.format("O empresa com nome fantasia %s não existe!", fantasia.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity listaEmpresaToUf(String uf) {

        Mensagem mensagem = new Mensagem();

        if (uf.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Uf não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var empresa = empresaRepository.findAllByUfContaining(removerAcentosEspacoBranco(
                uf.toUpperCase()));

        if (empresa.isEmpty()) {
            mensagem.setMessage(String.format("O empresa com uf %s não existe!", uf.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity listaEmpresaToMunicipio(String municipio) {

        Mensagem mensagem = new Mensagem();

        if (municipio.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Municipio não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var empresa = empresaRepository.findAllByMunicipioContaining(removerAcentosEspacoBranco(
                municipio.toUpperCase()));

        if (empresa.isEmpty()) {
            mensagem.setMessage(String.format("O empresa com municipio %s não existe!", municipio.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    public ResponseEntity listaEmpresaToUfMunicipio(String uf, String municipio) {

        Mensagem mensagem = new Mensagem();

        if (uf.replace(" ", "").isEmpty()){
            mensagem.setMessage(String.format("Uf não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        if (municipio.replace(" ", "").isEmpty()){
            mensagem.setMessage(String.format("Municipio não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        var empresaUf = empresaRepository.findAllByUfContaining(removerAcentosEspacoBranco(
                uf.toUpperCase()));

        List<Empresa> empresa = new ArrayList<>();

        for (Empresa emp : empresaUf) {
            if (removerAcentosEspacoBranco(emp.getMunicipio()).toUpperCase().
                    contains(removerAcentosEspacoBranco(municipio).toUpperCase()))
                empresa.add(emp);
        }


        if (empresa.isEmpty()) {
            mensagem.setMessage(String.format("O empresa com uf %s do municipio %s não existe!", uf.toUpperCase(), municipio.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    //empresaApiExterna
    @Autowired
    private EmpresasApiExternaService empresasApiExternaService;

    public ResponseEntity buscaEmpresaApiExterna(String cnpj) {

        Mensagem mensagem = new Mensagem();

        if (cnpj.replace(" ", "").isEmpty()){
            mensagem.setMessage(String.format("O cnpj esta vázio"));
            return new ResponseEntity<>(
                    mensagem,
                    HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            EmpresaApiExterna empresaSalvar = new EmpresaApiExterna();

            Triplet<RestTemplate, UriComponents, HttpEntity> resultEmpresaApiExtrena =
                    empresasApiExternaService.resultQueryEmpresaApiExterna("/v1/cnpj/",
                            removerAcentosEspacoBranco(cnpj));
            ResponseEntity<EmpresaApiExterna> empresaApiExternaResponse =
                    resultEmpresaApiExtrena.getValue0().exchange(
                            resultEmpresaApiExtrena.getValue1().toUriString(),
                            HttpMethod.GET,
                            resultEmpresaApiExtrena.getValue2(),
                            EmpresaApiExterna.class);

            empresaSalvar = empresaApiExternaResponse.getBody();

            Empresa empresaNew = empresasApiExternaService.empresa(empresaSalvar);

            if (empresaNew == null) {
                mensagem.setMessage(String.format("A empresa com cnpj %s não existe!", cnpj));
                return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(empresaNew, HttpStatus.OK);

        } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
            mensagem.setMessage(String.format("Api externa de Consulta de produto: %s", httpClientOrServerExc.getMessage()));
            return new ResponseEntity<>(
                    mensagem,
                    httpClientOrServerExc.getStatusCode());
        }
    }

    //preco
    @Autowired
    private PrecoRepository precoRepository;

    public ResponseEntity listaPreco() {

        Mensagem mensagem = new Mensagem();

        var listaPreco = precoRepository.findAll();

        if (listaPreco.isEmpty()){
            mensagem.setMessage("Lista preços vazia!");
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(listaPreco, HttpStatus.OK);
    }

    public ResponseEntity precoToId(String id) {
        Mensagem mensagem = new Mensagem();

        if (id.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var preco = precoRepository.findById(id);

        if (preco.isEmpty()) {
            mensagem.setMessage(String.format("O preço com id %s não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(preco, HttpStatus.OK);
    }

    public ResponseEntity precoToIdPro(String idProduto) {

        Mensagem mensagem = new Mensagem();

        if (idProduto.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id do produto não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if (produto == null) {
            mensagem.setMessage(String.format("Id %s produto não encontrado!", idProduto));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        var preco = precoRepository.findAllByProdutoId(idProduto);

        if (preco.size() < 0){
            mensagem.setMessage(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(), idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(preco, HttpStatus.OK);
    }

    public ResponseEntity precoToIdEmp(String idEmpresa) {

        Mensagem mensagem = new Mensagem();

        if (idEmpresa.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id da empresa não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            mensagem.setMessage(String.format("Id %s empresa não encontrado!", idEmpresa));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var preco = precoRepository.findAllByEmpresaId(idEmpresa);

        if (preco.size() < 0) {
            mensagem.setMessage(String.format("O preço das empresas %s com id %s não existe!",
                    empresa.getNome().toUpperCase(),
                    idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(preco, HttpStatus.OK);
    }

    public ResponseEntity precoToIdPrePro(String idPreco, String idProduto) {

        Mensagem mensagem = new Mensagem();

        if (idPreco.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id do preço não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        if (idProduto.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id do produto não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var preco = precoRepository.findById(idPreco);

        if (preco.isEmpty()) {
            mensagem.setMessage(String.format("O preço com id %s não existe!", idPreco.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Produto produto = produtoRepository.findById(idProduto).orElse(null);

        if (produto == null) {
            mensagem.setMessage(String.format("Id %s produto não encontrado!", idProduto));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var precosProduto = precoRepository.findAllByProdutoId(idProduto);

        if (precosProduto.size() < 0) {
            mensagem.setMessage(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(), idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Preco precoEncontrado = new Preco();
        for (Preco pre : precosProduto) {
            if (pre.getId().equals(preco.get().getId()))
                precoEncontrado = pre;
        }

        if (precoEncontrado == null) {
            mensagem.setMessage(String.format("O preço com produto s% não existe!",
                    produto.getNome().toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(precoEncontrado, HttpStatus.OK);
    }

    public ResponseEntity precoToIdPreEmp(String idPreco, String idEmpresa) {

        Mensagem mensagem = new Mensagem();

        if (idPreco.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id do preço não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        if (idEmpresa.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id da empresa não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        var preco = precoRepository.findById(idPreco);
        if (preco.isEmpty()) {
            mensagem.setMessage(String.format("O preço com id %s não existe!", idPreco.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            mensagem.setMessage(String.format("Id %s empresa não encontrado!", idEmpresa));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var precosEmpresa = precoRepository.findAllByEmpresaId(idEmpresa);

        if (precosEmpresa.size() < 0) {
            mensagem.setMessage(String.format("O preço das empresas %s com id s% não existe!",
                    empresa.getNome().toUpperCase(), idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Preco precoEncontrado = new Preco();
        for (Preco pre : precosEmpresa) {
            if (pre.getId().equals(preco.get().getId()))
                precoEncontrado = pre;
        }
        if (precoEncontrado == null) {
            mensagem.setMessage(String.format("O preço com empresa s% não existe!",
                    empresa.getNome().toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(precoEncontrado, HttpStatus.OK);
    }

    public ResponseEntity precoToIdProEmp(String idProduto, String idEmpresa) {

        Mensagem mensagem = new Mensagem();

        if (idProduto.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id do produto não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        if (idEmpresa.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id da empresa não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if (produto == null) {
            mensagem.setMessage(String.format("O produto com id %s não existe!", idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            mensagem.setMessage(String.format("Id %s empresa não encontrado!", idEmpresa));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var precosProduto = precoRepository.findAllByProdutoId(idProduto);

        if (precosProduto.size() < 0) {
            mensagem.setMessage(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(), idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var precosEmpresa = precoRepository.findAllByEmpresaId(idEmpresa);

        if (precosEmpresa.size() < 0) {
            mensagem.setMessage(String.format("O preço das empresas %s com id s% não existe!",
                    empresa.getNome().toUpperCase(), idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        List<Preco> precosEncontrado = new ArrayList<>();
        for (Preco prePro : precosProduto) {
            for (Preco preEmp : precosEmpresa) {
                if (prePro.getId().equals(preEmp.getId())) {
                    precosEncontrado.add(prePro);
                }
            }
        }

        if (precosEncontrado.size() < 0) {
            mensagem.setMessage(String.format("O preço da empresa %s com produto s% não existe!",
                    empresa.getNome().toUpperCase(), produto.getNome().toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(precosEncontrado, HttpStatus.OK);
    }

    public ResponseEntity precoToIdPreProEmp(String idPreco, String idProduto, String idEmpresa) {

        Mensagem mensagem = new Mensagem();

        if (idPreco.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id do preço não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        if (idProduto.replace(" ", "").isEmpty()){
            mensagem.setMessage(String.format("Id do produto não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        if (idEmpresa.replace(" ", "").isEmpty()) {
            mensagem.setMessage(String.format("Id da empresa não pode ser vazio"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }

        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if (produto == null) {
            mensagem.setMessage(String.format("O produto com id %s não existe!", idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if (empresa == null) {
            mensagem.setMessage(String.format("Id %s empresa não encontrado!", idEmpresa));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var preco = precoRepository.findById(idPreco);
        if (preco.isEmpty()) {
            mensagem.setMessage(String.format("O preço com id %s não existe!", idPreco.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var precosProduto = precoRepository.findAllByProdutoId(idProduto);

        if (precosProduto.size() < 0) {
            mensagem.setMessage(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(), idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        var precosEmpresa = precoRepository.findAllByEmpresaId(idEmpresa);

        if (precosEmpresa.size() < 0) {
            mensagem.setMessage(String.format("O preço das empresas %s com id s% não existe!",
                    empresa.getNome().toUpperCase(), idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        Preco precoEncontrado = new Preco();
        for (Preco prePro : precosProduto) {
            for (Preco preEmp : precosEmpresa) {
                if (prePro.getId().equals(preEmp.getId())) {
                    if (prePro.getId().equals(preco.get().getId())) {
                        precoEncontrado = prePro;
                    }
                }
            }
        }


        if (precoEncontrado == null) {
            mensagem.setMessage(String.format("O preço com produto s% e empresa %s não existe!",
                    produto.getNome().toUpperCase(),
                    empresa.getNome().toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(precoEncontrado, HttpStatus.OK);
    }

    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }


}
