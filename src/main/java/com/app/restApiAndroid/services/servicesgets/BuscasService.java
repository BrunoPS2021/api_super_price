package com.app.restApiAndroid.services.servicesgets;

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

        var listUsers =  dadosUsuarioRepository.findAll();

        if(listUsers.isEmpty())
            return new ResponseEntity("Lista usuários vazia!",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(listUsers, HttpStatus.OK);
    }
    public ResponseEntity listUserToName(String name){

        if(name.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var user = dadosUsuarioRepository.findAllByNameContaining(removerAcentosEspacoBranco(
                name.toUpperCase()));

        if(user.isEmpty())
            return new ResponseEntity(String.format("O usuário %s não existe!",name.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity userToName(String name){

        if(name.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var user = dadosUsuarioRepository.findByName(removerAcentosEspacoBranco(name.toUpperCase()));

        if(user == null)
            return new ResponseEntity(String.format("O usuário %s não existe!",name.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity userToId(String id){

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var user = dadosUsuarioRepository.findAllByUserId(id);

        if(user.isEmpty())
            return new ResponseEntity(String.format("O usuário com id %s não existe!",id.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity userToLogin(String login){
        if(login.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Login não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var user = dadosUsuarioRepository.findAllByUserLogin(removerAcentosEspacoBranco(
                login.toUpperCase()));

        if(user.isEmpty())
            return new ResponseEntity(String.format("O Login %s não existe!",login.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity userToEmail(String email){
        if(email.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Email não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var user = dadosUsuarioRepository.findByEmail(removerAcentosEspacoBranco(
                email.toUpperCase()));

        if(user == null)
            return new ResponseEntity(String.format("O email %s não existe!",email.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity userToLoginEmail(String loginEmail){
        if(loginEmail.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Login ou email não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var user = dadosUsuarioRepository.findByEmail(removerAcentosEspacoBranco(
                loginEmail.toUpperCase()));

        if(user == null){
            user = dadosUsuarioRepository.findByUserLogin(removerAcentosEspacoBranco(
                    loginEmail.toUpperCase()));

            if(user == null)
                return new ResponseEntity(String.format("O login ou email %s não existe!",loginEmail.toUpperCase()),HttpStatus.NOT_FOUND);
        }


        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    //produto
    @Autowired
    private ProdutoRepository produtoRepository;
    public ResponseEntity listaProdutos() {

        var listaProduto =  produtoRepository.findAll();

        if(listaProduto.isEmpty())
            return new ResponseEntity("Lista produto vazia!",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(listaProduto, HttpStatus.OK);
    }
    public ResponseEntity listaProdutosToTipo(String tipo){

        if(tipo.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Tipo não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var listaProdutoToTipo = produtoRepository.findAllByTipoProdutoContaining(
                removerAcentosEspacoBranco(tipo.toUpperCase()));

        if(listaProdutoToTipo.isEmpty())
            return new ResponseEntity(String.format("O produtos do %s não existe!",tipo.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(listaProdutoToTipo, HttpStatus.OK);
    }
    public ResponseEntity produtoToNome(String nome){

        if(nome.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var produtoToNome = produtoRepository.findByNome(removerAcentosEspacoBranco(
                nome.toUpperCase()));

        if(produtoToNome == null)
            return new ResponseEntity(String.format("O produtos do %s não existe!",nome.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(produtoToNome, HttpStatus.OK);
    }
    public ResponseEntity listaProdutoToNome(String nome){

        if(nome.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var produto = produtoRepository.findAllByNomeContaining(removerAcentosEspacoBranco(
                nome.toUpperCase()));

        if(produto.isEmpty())
            return new ResponseEntity(String.format("O produto %s não existe!",nome.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
    public ResponseEntity produtoToId(String id){

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var produto = produtoRepository.findById(id);

        if(produto.isEmpty())
            return new ResponseEntity(String.format("O produto com id %s não existe!",id.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }
    public ResponseEntity produtoToGtin(String gtin){

        if(gtin.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Código de barras não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var produto = produtoRepository.findByGtin(gtin.toUpperCase());

        if(produto == null)
            return new ResponseEntity(String.format("O produto com código de barras %s não existe!",gtin.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    //produtoApiExterna
    @Autowired
    private ProdutosApiExternaService produtosApiExternaService;
    public ResponseEntity buscaProdutoApiExterna(String gtin){

        if(gtin.replace(" ","").isEmpty())
            return new ResponseEntity(
                    String.format("O código de barras esta vázio"),
                    HttpStatus.NOT_ACCEPTABLE);

        try
        {
            ProdutoApiExterna produtoSalvar = new ProdutoApiExterna();

            Triplet<RestTemplate,UriComponents,HttpEntity> resultProdutoApiExtrena =
                    produtosApiExternaService.resultQueryProdutoApiExterna("gtins/%s",
                            removerAcentosEspacoBranco(gtin));
            ResponseEntity<ProdutoApiExterna> produtoApiExternaResponse =
                    resultProdutoApiExtrena.getValue0().exchange(
                            resultProdutoApiExtrena.getValue1().toUriString(),
                            HttpMethod.GET,
                            resultProdutoApiExtrena.getValue2(),
                            ProdutoApiExterna.class);

            produtoSalvar = produtoApiExternaResponse.getBody();

            Produto  produtoNew = produtosApiExternaService.produto(produtoSalvar);

            return new ResponseEntity(produtoNew, HttpStatus.OK);
        }
        catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc){
            return new ResponseEntity(
                    String.format("Api externa de Consulta de produto: %s",httpClientOrServerExc.getMessage()),
                    httpClientOrServerExc.getStatusCode());
        }
    }

    //emprasa
    @Autowired
    private EmpresaRepository empresaRepository;
    public ResponseEntity listaEmpresas() {

        var listaEmpresa =  empresaRepository.findAll();

        if(listaEmpresa.isEmpty())
            return new ResponseEntity("Lista empresa vazia!",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(listaEmpresa, HttpStatus.OK);
    }
    public ResponseEntity empresaToId(String id){

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var empresa = empresaRepository.findById(id);

        if(empresa.isEmpty())
            return new ResponseEntity(String.format("A empresa com id %s não existe!",id.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity empresaToCnpj(String cnpj){

        if(cnpj.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Cnpj não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var empresa = empresaRepository.findByCnpj(cnpj);

        if(empresa == null)
            return new ResponseEntity(String.format("A empresa com cnpj %s não existe!",cnpj.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity empresaToNome(String nome){

        if(nome.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var empresa = empresaRepository.findByNome(removerAcentosEspacoBranco(nome.toUpperCase()));

        if(empresa == null)
            return new ResponseEntity(String.format("A empresa %s não existe!",nome.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity empresaToFantasia(String fantasia){

        if(fantasia.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome fantasia não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var empresa = empresaRepository.findByFantasia(removerAcentosEspacoBranco(fantasia.toUpperCase()));

        if(empresa == null)
            return new ResponseEntity(String.format("A empresa com nome fantasia %s não existe!",fantasia.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity listaEmpresaToNome(String nome){

        if(nome.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var empresa = empresaRepository.findAllByNomeContaining(removerAcentosEspacoBranco(
                nome.toUpperCase()));

        if(empresa.isEmpty())
            return new ResponseEntity(String.format("A empresa %s não existe!",nome.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity listaEmpresaToFantasia(String fantasia){

        if(fantasia.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome fantasia não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var empresa = empresaRepository.findAllByFantasiaContaining(removerAcentosEspacoBranco(
                fantasia.toUpperCase()));

        if(empresa.isEmpty())
            return new ResponseEntity(String.format("O empresa com nome fantasia %s não existe!",fantasia.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity listaEmpresaToUf(String uf){

        if(uf.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Uf não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var empresa = empresaRepository.findAllByUfContaining(removerAcentosEspacoBranco(
                uf.toUpperCase()));

        if(empresa.isEmpty())
            return new ResponseEntity(String.format("O empresa com uf %s não existe!",uf.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity listaEmpresaToMunicipio(String municipio){

        if(municipio.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Municipio não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        var empresa = empresaRepository.findAllByMunicipioContaining(removerAcentosEspacoBranco(
                municipio.toUpperCase()));

        if(empresa.isEmpty())
            return new ResponseEntity(String.format("O empresa com municipio %s não existe!",municipio.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }
    public ResponseEntity listaEmpresaToUfMunicipio(String uf, String municipio){

        if(uf.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Uf não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);
        if(municipio.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Municipio não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var empresaUf = empresaRepository.findAllByUfContaining(removerAcentosEspacoBranco(
                uf.toUpperCase()));

        List<Empresa> empresa = new ArrayList<>();

        for (Empresa emp : empresaUf) {
            if(removerAcentosEspacoBranco(emp.getMunicipio()).toUpperCase().
                    contains(removerAcentosEspacoBranco(municipio).toUpperCase()))
                empresa.add(emp);
        }


        if(empresa.isEmpty())
            return new ResponseEntity(String.format("O empresa com uf %s do municipio %s não existe!",uf.toUpperCase(),municipio.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(empresa, HttpStatus.OK);
    }

    //empresaApiExterna
    @Autowired
    private EmpresasApiExternaService empresasApiExternaService;
    public ResponseEntity buscaEmpresaApiExterna(String cnpj){

        if(cnpj.replace(" ","").isEmpty())
            return new ResponseEntity(
                    String.format("O cnpj esta vázio"),
                    HttpStatus.NOT_ACCEPTABLE);

        try
        {
            EmpresaApiExterna empresaSalvar = new EmpresaApiExterna();

            Triplet<RestTemplate,UriComponents,HttpEntity> resultEmpresaApiExtrena =
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

            if(empresaNew == null)
                return new ResponseEntity(String.format("A empresa com cnpj %s não existe!",cnpj),HttpStatus.NOT_FOUND);

            return new ResponseEntity(empresaNew, HttpStatus.OK);
        }
        catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc){
            return new ResponseEntity(
                    String.format("Api externa de Consulta de produto: %s",httpClientOrServerExc.getMessage()),
                    httpClientOrServerExc.getStatusCode());
        }
    }

    //preco
    @Autowired
    private PrecoRepository precoRepository;
    public ResponseEntity listaPreco() {

        var listaPreco =  precoRepository.findAll();

        if(listaPreco.isEmpty())
            return new ResponseEntity("Lista preços vazia!",HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(listaPreco, HttpStatus.OK);
    }
    public ResponseEntity precoToId(String id){

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var preco = precoRepository.findById(id);

        if(preco.isEmpty())
            return new ResponseEntity(String.format("O preço com id %s não existe!",id.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(preco, HttpStatus.OK);
    }
    public ResponseEntity precoToIdPro(String idProduto){

        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do produto não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if(produto == null)
            return new ResponseEntity(String.format("Id %s produto não encontrado!",idProduto),HttpStatus.NOT_FOUND);
        var preco = precoRepository.findAllByProdutoId(idProduto);

        if(preco.size() < 0)
            return new ResponseEntity(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(),idProduto.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(preco, HttpStatus.OK);
    }
    public ResponseEntity precoToIdEmp(String idEmpresa){

        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id da empresa não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if(empresa == null)
            return new ResponseEntity(String.format("Id %s empresa não encontrado!",idEmpresa),HttpStatus.NOT_FOUND);
        var preco = precoRepository.findAllByEmpresaId(idEmpresa);

        if(preco.size() < 0)
            return new ResponseEntity(String.format("O preço das empresas %s com id %s não existe!",
                    empresa.getNome().toUpperCase(),
                    idEmpresa.toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(preco, HttpStatus.OK);
    }
    public ResponseEntity precoToIdPrePro(String idPreco,String idProduto){

        if(idPreco.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do preço não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);
        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do produto não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var preco = precoRepository.findById(idPreco);
        if(preco.isEmpty())
            return new ResponseEntity(String.format("O preço com id %s não existe!",idPreco.toUpperCase()),HttpStatus.NOT_FOUND);

        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if(produto == null)
            return new ResponseEntity(String.format("Id %s produto não encontrado!",idProduto),HttpStatus.NOT_FOUND);

        var precosProduto = precoRepository.findAllByProdutoId(idProduto);

        if(precosProduto.size() < 0)
            return new ResponseEntity(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(),idProduto.toUpperCase()),HttpStatus.NOT_FOUND);

        Preco precoEncontrado = new Preco();
        for(Preco pre : precosProduto){
            if(pre.getId().equals(preco.get().getId()))
                precoEncontrado = pre;
        }

        if(precoEncontrado == null)
            return new ResponseEntity(String.format("O preço com produto s% não existe!",
                    produto.getNome().toUpperCase()),HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(precoEncontrado, HttpStatus.OK);
    }
    public ResponseEntity precoToIdPreEmp(String idPreco,String idEmpresa){

        if(idPreco.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do preço não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);
        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id da empresa não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        var preco = precoRepository.findById(idPreco);
        if(preco.isEmpty())
            return new ResponseEntity(String.format("O preço com id %s não existe!",idPreco.toUpperCase()),HttpStatus.NOT_FOUND);

        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if(empresa == null)
            return new ResponseEntity(String.format("Id %s empresa não encontrado!",idEmpresa),HttpStatus.NOT_FOUND);

        var precosEmpresa = precoRepository.findAllByEmpresaId(idEmpresa);

        if(precosEmpresa.size() < 0)
            return new ResponseEntity(String.format("O preço das empresas %s com id s% não existe!",
                    empresa.getNome().toUpperCase(),idEmpresa.toUpperCase()),HttpStatus.NOT_FOUND);

        Preco precoEncontrado = new Preco();
        for(Preco pre : precosEmpresa){
            if(pre.getId().equals(preco.get().getId()))
                precoEncontrado = pre;
        }
        if(precoEncontrado == null)
            return new ResponseEntity(String.format("O preço com empresa s% não existe!",
                    empresa.getNome().toUpperCase()),HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(precoEncontrado, HttpStatus.OK);
    }
    public ResponseEntity precoToIdProEmp(String idProduto,String idEmpresa){

        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do produto não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);
        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id da empresa não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if(produto == null)
            return new ResponseEntity(String.format("O produto com id %s não existe!",idProduto.toUpperCase()),HttpStatus.NOT_FOUND);

        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if(empresa == null)
            return new ResponseEntity(String.format("Id %s empresa não encontrado!",idEmpresa),HttpStatus.NOT_FOUND);

        var precosProduto = precoRepository.findAllByProdutoId(idProduto);

        if(precosProduto.size() < 0)
            return new ResponseEntity(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(),idProduto.toUpperCase()),HttpStatus.NOT_FOUND);

        var precosEmpresa = precoRepository.findAllByEmpresaId(idEmpresa);

        if(precosEmpresa.size() < 0)
            return new ResponseEntity(String.format("O preço das empresas %s com id s% não existe!",
                    empresa.getNome().toUpperCase(),idEmpresa.toUpperCase()),HttpStatus.NOT_FOUND);

        List<Preco> precosEncontrado = new ArrayList<>();
        for(Preco prePro : precosProduto){
            for(Preco preEmp: precosEmpresa){
            if(prePro.getId().equals(preEmp.getId())){
                precosEncontrado.add(prePro);}}
        }

        if(precosEncontrado.size() < 0)
            return new ResponseEntity(String.format("O preço da empresa %s com produto s% não existe!",
                    empresa.getNome().toUpperCase(),produto.getNome().toUpperCase()),HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(precosEncontrado, HttpStatus.OK);
    }
    public ResponseEntity precoToIdPreProEmp(String idPreco, String idProduto,String idEmpresa){

        if(idPreco.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do preço não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do produto não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);
        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id da empresa não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        Produto produto = produtoRepository.findById(idProduto).orElse(null);
        if(produto == null)
            return new ResponseEntity(String.format("O produto com id %s não existe!",idProduto.toUpperCase()),HttpStatus.NOT_FOUND);

        Empresa empresa = empresaRepository.findById(idEmpresa).orElse(null);
        if(empresa == null)
            return new ResponseEntity(String.format("Id %s empresa não encontrado!",idEmpresa),HttpStatus.NOT_FOUND);

        var preco = precoRepository.findById(idPreco);
        if(preco.isEmpty())
            return new ResponseEntity(String.format("O preço com id %s não existe!",idPreco.toUpperCase()),HttpStatus.NOT_FOUND);

        var precosProduto = precoRepository.findAllByProdutoId(idProduto);

        if(precosProduto.size() < 0)
            return new ResponseEntity(String.format("O preço dos produtos %s com id s% não existe!",
                    produto.getNome().toUpperCase(),idProduto.toUpperCase()),HttpStatus.NOT_FOUND);

        var precosEmpresa = precoRepository.findAllByEmpresaId(idEmpresa);

        if(precosEmpresa.size() < 0)
            return new ResponseEntity(String.format("O preço das empresas %s com id s% não existe!",
                    empresa.getNome().toUpperCase(),idEmpresa.toUpperCase()),HttpStatus.NOT_FOUND);

        Preco precoEncontrado = new Preco();
        for(Preco prePro : precosProduto){
            for(Preco preEmp: precosEmpresa){
                if(prePro.getId().equals(preEmp.getId()))
                {
                    if(prePro.getId().equals(preco.get().getId())){
                        precoEncontrado = prePro;
                    }
                }
            }
        }


        if(precoEncontrado == null)
            return new ResponseEntity(String.format("O preço com produto s% e empresa %s não existe!",
                    produto.getNome().toUpperCase(),
                    empresa.getNome().toUpperCase()),HttpStatus.NOT_FOUND);


        return new ResponseEntity<>(precoEncontrado, HttpStatus.OK);
    }

    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }


}
