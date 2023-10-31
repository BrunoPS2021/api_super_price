package com.app.restApiAndroid.services.servicesdeletes;

import com.app.restApiAndroid.models.Mensagem;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.preco.Preco;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.models.usuario.DadosUsuario;
import com.app.restApiAndroid.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DeletesService {
    //usuario
    @Autowired
    private DadosUsuarioRepository dadosUsersRepositor;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public ResponseEntity deletarUserToId(String id)  {

        Mensagem mensagem = new Mensagem();

        if(id.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        DadosUsuario dadosUsuarioDel =  this.dadosUsersRepositor.findByUserId(id.toUpperCase());
        
        if(dadosUsuarioDel == null) {
            mensagem.setMessage(String.format("Id %s informado não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.usuarioRepository.deleteById(dadosUsuarioDel.getUser().getId().toUpperCase());

        mensagem.setMessage(String.format("Usuário %s deletado com sucesso ID(%s)!", dadosUsuarioDel.getName().toUpperCase(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarUserToLogin(String login)  {
        Mensagem mensagem = new Mensagem();

        if(login.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o login!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        DadosUsuario dadosUsuarioDel =  this.dadosUsersRepositor.findByUserLogin(login.toUpperCase());

        if(dadosUsuarioDel == null) {
            mensagem.setMessage(String.format("O Login %s informado não existe!", login.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.usuarioRepository.deleteById(dadosUsuarioDel.getUser().getId().toUpperCase());

        mensagem.setMessage(String.format("Usuário %s deletado com sucesso ID(%s)!", dadosUsuarioDel.getName().toUpperCase(), dadosUsuarioDel.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    //produto
    @Autowired
    private ProdutoRepository produtoRepository;
    public ResponseEntity deletarProdutoToId(String id)  {
        Mensagem mensagem = new Mensagem();

        if(id.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Produto produtoDelete =  this.produtoRepository.findById(id.toUpperCase()).orElse(null);

        if(produtoDelete == null) {
            mensagem.setMessage(String.format("Id %s informado não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.produtoRepository.deleteById(produtoDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Produto %s deletado com sucesso ID(%s)!", produtoDelete.getNome().toUpperCase(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarProdutoToGtin(String gtin)  {

        Mensagem mensagem = new Mensagem();

        if(gtin.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o código de barras!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Produto produtoDelete =  this.produtoRepository.findByGtin(gtin.toUpperCase());

        if(produtoDelete == null) {
            mensagem.setMessage(String.format("O código de barras %s informado não existe!", gtin.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.produtoRepository.deleteById(produtoDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Produto %s deletado com sucesso ID(%s)!", produtoDelete.getNome().toUpperCase(), produtoDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    //empresa
    @Autowired
    private EmpresaRepository empresaRepository;
    public ResponseEntity deletarEmpresaToId(String id)  {
        Mensagem mensagem = new Mensagem();

        if(id.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Empresa empresaDelete =  this.empresaRepository.findById(id.toUpperCase()).orElse(null);

        if(empresaDelete == null) {
            mensagem.setMessage(String.format("Id %s informado não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarEmpresaToCnpj(String cnpj)  {
        Mensagem mensagem = new Mensagem();

        if(cnpj.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o cnpj!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Empresa empresaDelete =  this.empresaRepository.findByCnpj(cnpj.toUpperCase());

        if(empresaDelete == null) {
            mensagem.setMessage(String.format("Cnpj %s informado não existe!", cnpj.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),empresaDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarEmpresaToNome(String nome)  {
        Mensagem mensagem = new Mensagem();

        if(nome.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o nome!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Empresa empresaDelete =  this.empresaRepository.findByNome(nome.toUpperCase());

        if(empresaDelete == null) {
            mensagem.setMessage(String.format("Nome %s informado não existe!", nome.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),empresaDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarEmpresaToFantasia(String fantasia)  {
        Mensagem mensagem = new Mensagem();

        if(fantasia.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o nome fantasia!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Empresa empresaDelete =  this.empresaRepository.findByFantasia(fantasia.toUpperCase());

        if(empresaDelete == null) {
            mensagem.setMessage(String.format("Nome fantasia %s informado não existe!", fantasia.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),empresaDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    //preco
    @Autowired
    private PrecoRepository precoRepository;
    public ResponseEntity deletarPrecoToId(String id)  {
        Mensagem mensagem = new Mensagem();

        if(id.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        Preco precoDelete =  this.precoRepository.findById(id.toUpperCase()).orElse(null);

        if(precoDelete == null) {
            mensagem.setMessage(String.format("Id %s informado não existe!", id.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        this.precoRepository.deleteById(precoDelete.getId().toUpperCase());

        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoDelete.getProduto().getNome().toUpperCase(),
                precoDelete.getEmpresa().getNome().toUpperCase(),
                id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPro(String idProduto)  {
        Mensagem mensagem = new Mensagem();

        if(idProduto.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        List<Preco> precoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());

        if(precoDelete.isEmpty()) {
            mensagem.setMessage(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        for(Preco preco : precoDelete){

            this.precoRepository.deleteById(preco.getId().toUpperCase());
        }

        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoDelete.get(0).getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdEmp(String idEmpresa)  {
        Mensagem mensagem = new Mensagem();

        if(idEmpresa.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        List<Preco> precoDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoDelete.isEmpty()) {
            mensagem.setMessage(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);
        }
        for(Preco preco : precoDelete){

            this.precoRepository.deleteById(preco.getId().toUpperCase());
        }
        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoDelete.get(0).getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPrePro(String idPreco,String idProduto) {
        Mensagem mensagem = new Mensagem();

        if(idPreco.replace(" ","").isEmpty()) {
            mensagem.setMessage(String.format("Informe o id do preço!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
            if(idProduto.replace(" ","").isEmpty()) {
                mensagem.setMessage(String.format("Informe o id do produto!"));
                return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
            }
        Preco precoPrecoDelete = this.precoRepository.findById(idPreco).orElse(null);
        List<Preco> precoProdutoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());

        if(precoPrecoDelete == null){
            mensagem.setMessage(String.format("Id do preço %s informado não existe!",
                    idPreco.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}
        else if(precoProdutoDelete.isEmpty()){
            mensagem.setMessage(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}


        for(Preco precoProduto : precoProdutoDelete){
            if(precoProduto.getId().equals(precoPrecoDelete.getId())){
                  this.precoRepository.deleteById(precoProduto.getId().toUpperCase());
            }
        }

        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoProdutoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoPrecoDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPreEmp(String idPreco,String idEmpresa) {
        Mensagem mensagem = new Mensagem();

        if(idPreco.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id do preço!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}
        if(idEmpresa.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id da empresa!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}

        Preco precoPrecoDelete = this.precoRepository.findById(idPreco).orElse(null);
        List<Preco> precoEmpresaDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoPrecoDelete == null){
            mensagem.setMessage(String.format("Id do preço %s informado não existe!",
                    idPreco.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}
        else if(precoEmpresaDelete.isEmpty()){
            mensagem.setMessage(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}


        for(Preco precoEmpresa : precoEmpresaDelete){
            if(precoEmpresa.getId().equals(precoPrecoDelete.getId())){
                this.precoRepository.deleteById(precoEmpresa.getId().toUpperCase());
            }
        }
        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoEmpresaDelete.get(0).getProduto().getNome().toUpperCase(),
                precoEmpresaDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoPrecoDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdProEmp(String idProduto,String idEmpresa)  {
        Mensagem mensagem = new Mensagem();

        if(idProduto.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id do produto!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}

        if(idEmpresa.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id da empresa!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}


        List<Preco> precoProdutoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());
        List<Preco> precoEmpresaDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoProdutoDelete.isEmpty()){
            mensagem.setMessage(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}
        else if(precoEmpresaDelete.isEmpty()){
            mensagem.setMessage(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}


        for(Preco precoProduto : precoProdutoDelete){
            for(Preco precoEmpresa : precoEmpresaDelete){
                if(precoProduto.getId().equals(precoEmpresa.getId())){
                    this.precoRepository.deleteById(precoProduto.getId().toUpperCase());
                }
            }
        }
        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoProdutoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPreProEmp(String idPreco,String idProduto,String idEmpresa)  {

        Mensagem mensagem = new Mensagem();

        if(idPreco.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id do preço!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}
        if(idProduto.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id do produto!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}
        if(idEmpresa.replace(" ","").isEmpty()){
            mensagem.setMessage(String.format("Informe o id da empresa!"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);}

        Preco precoPrecoDelete = this.precoRepository.findById(idPreco).orElse(null);
        List<Preco> precoProdutoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());
        List<Preco> precoEmpresaDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoPrecoDelete == null){
            mensagem.setMessage(String.format("Id do preço %s informado não existe!",
                    idPreco.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}
        else if(precoProdutoDelete.isEmpty()){
            mensagem.setMessage(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}
        else if(precoEmpresaDelete.isEmpty()){
            mensagem.setMessage(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_FOUND);}


        for(Preco precoProduto : precoProdutoDelete){
            for(Preco precoEmpresa : precoEmpresaDelete){
                if(precoProduto.getId().equals(precoEmpresa.getId())){
                    if(precoProduto.getId().equals(precoPrecoDelete.getId())){
                        this.precoRepository.deleteById(precoProduto.getId().toUpperCase());
                    }
                }
            }
        }
        mensagem.setMessage(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoProdutoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoPrecoDelete.getId().toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

}
