package com.app.restApiAndroid.services.servicesdeletes;

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

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id!"), HttpStatus.NOT_ACCEPTABLE);

        DadosUsuario dadosUsuarioDel =  this.dadosUsersRepositor.findByUserId(id.toUpperCase());
        
        if(dadosUsuarioDel == null)
            return new ResponseEntity(String.format("Id %s informado não existe!",id.toUpperCase()), HttpStatus.NOT_FOUND);

        this.usuarioRepository.deleteById(dadosUsuarioDel.getUser().getId().toUpperCase());

        return new ResponseEntity(String.format("Usuário %s deletado com sucesso ID(%s)!", dadosUsuarioDel.getName().toUpperCase(),id.toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarUserToLogin(String login)  {

        if(login.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o login!"), HttpStatus.NOT_ACCEPTABLE);

        DadosUsuario dadosUsuarioDel =  this.dadosUsersRepositor.findByUserLogin(login.toUpperCase());

        if(dadosUsuarioDel == null)
            return new ResponseEntity(String.format("O Login %s informado não existe!",login.toUpperCase()), HttpStatus.NOT_FOUND);

        this.usuarioRepository.deleteById(dadosUsuarioDel.getUser().getId().toUpperCase());

        return new ResponseEntity(String.format("Usuário %s deletado com sucesso ID(%s)!", dadosUsuarioDel.getName().toUpperCase(), dadosUsuarioDel.getId().toUpperCase()), HttpStatus.CREATED);
    }

    //produto
    @Autowired
    private ProdutoRepository produtoRepository;
    public ResponseEntity deletarProdutoToId(String id)  {

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id!"), HttpStatus.NOT_ACCEPTABLE);

        Produto produtoDelete =  this.produtoRepository.findById(id.toUpperCase()).orElse(null);

        if(produtoDelete == null)
            return new ResponseEntity(String.format("Id %s informado não existe!",id.toUpperCase()), HttpStatus.NOT_FOUND);

        this.produtoRepository.deleteById(produtoDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Produto %s deletado com sucesso ID(%s)!", produtoDelete.getNome().toUpperCase(),id.toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarProdutoToGtin(String gtin)  {

        if(gtin.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o código de barras!"), HttpStatus.NOT_ACCEPTABLE);

        Produto produtoDelete =  this.produtoRepository.findByGtin(gtin.toUpperCase());

        if(produtoDelete == null)
            return new ResponseEntity(String.format("O código de barras %s informado não existe!",gtin.toUpperCase()), HttpStatus.NOT_FOUND);

        this.produtoRepository.deleteById(produtoDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Produto %s deletado com sucesso ID(%s)!", produtoDelete.getNome().toUpperCase(), produtoDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }

    //empresa
    @Autowired
    private EmpresaRepository empresaRepository;
    public ResponseEntity deletarEmpresaToId(String id)  {

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id!"), HttpStatus.NOT_ACCEPTABLE);

        Empresa empresaDelete =  this.empresaRepository.findById(id.toUpperCase()).orElse(null);

        if(empresaDelete == null)
            return new ResponseEntity(String.format("Id %s informado não existe!",id.toUpperCase()), HttpStatus.NOT_FOUND);

        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),id.toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarEmpresaToCnpj(String cnpj)  {

        if(cnpj.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o cnpj!"), HttpStatus.NOT_ACCEPTABLE);

        Empresa empresaDelete =  this.empresaRepository.findByCnpj(cnpj.toUpperCase());

        if(empresaDelete == null)
            return new ResponseEntity(String.format("Cnpj %s informado não existe!",cnpj.toUpperCase()), HttpStatus.NOT_FOUND);

        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),empresaDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarEmpresaToNome(String nome)  {

        if(nome.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o nome!"), HttpStatus.NOT_ACCEPTABLE);

        Empresa empresaDelete =  this.empresaRepository.findByNome(nome.toUpperCase());

        if(empresaDelete == null)
            return new ResponseEntity(String.format("Nome %s informado não existe!",nome.toUpperCase()), HttpStatus.NOT_FOUND);

        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),empresaDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarEmpresaToFantasia(String fantasia)  {

        if(fantasia.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o nome fantasia!"), HttpStatus.NOT_ACCEPTABLE);

        Empresa empresaDelete =  this.empresaRepository.findByFantasia(fantasia.toUpperCase());

        if(empresaDelete == null)
            return new ResponseEntity(String.format("Nome fantasia %s informado não existe!",fantasia.toUpperCase()), HttpStatus.NOT_FOUND);

        this.empresaRepository.deleteById(empresaDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Empresa %s deletado com sucesso ID(%s)!", empresaDelete.getNome().toUpperCase(),empresaDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }

    //preco
    @Autowired
    private PrecoRepository precoRepository;
    public ResponseEntity deletarPrecoToId(String id)  {

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id!"), HttpStatus.NOT_ACCEPTABLE);

        Preco precoDelete =  this.precoRepository.findById(id.toUpperCase()).orElse(null);

        if(precoDelete == null)
            return new ResponseEntity(String.format("Id %s informado não existe!",id.toUpperCase()), HttpStatus.NOT_FOUND);

        this.precoRepository.deleteById(precoDelete.getId().toUpperCase());

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoDelete.getProduto().getNome().toUpperCase(),
                precoDelete.getEmpresa().getNome().toUpperCase(),
                id.toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPro(String idProduto)  {

        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id!"), HttpStatus.NOT_ACCEPTABLE);

        List<Preco> precoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());

        if(precoDelete.isEmpty())
            return new ResponseEntity(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()), HttpStatus.NOT_FOUND);

        for(Preco preco : precoDelete){

            this.precoRepository.deleteById(preco.getId().toUpperCase());
        }

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoDelete.get(0).getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdEmp(String idEmpresa)  {

        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id!"), HttpStatus.NOT_ACCEPTABLE);

        List<Preco> precoDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoDelete.isEmpty())
            return new ResponseEntity(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()), HttpStatus.NOT_FOUND);

        for(Preco preco : precoDelete){

            this.precoRepository.deleteById(preco.getId().toUpperCase());
        }

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoDelete.get(0).getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPrePro(String idPreco,String idProduto) {

        if(idPreco.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id do preço!"), HttpStatus.NOT_ACCEPTABLE);
        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id do produto!"), HttpStatus.NOT_ACCEPTABLE);

        Preco precoPrecoDelete = this.precoRepository.findById(idPreco).orElse(null);
        List<Preco> precoProdutoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());

        if(precoPrecoDelete == null)
            return new ResponseEntity(String.format("Id do preço %s informado não existe!",
                    idPreco.toUpperCase()), HttpStatus.NOT_FOUND);
        else if(precoProdutoDelete.isEmpty())
            return new ResponseEntity(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()), HttpStatus.NOT_FOUND);


        for(Preco precoProduto : precoProdutoDelete){
            if(precoProduto.getId().equals(precoPrecoDelete.getId())){
                  this.precoRepository.deleteById(precoProduto.getId().toUpperCase());
            }
        }

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoProdutoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoPrecoDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPreEmp(String idPreco,String idEmpresa) {

        if(idPreco.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id do preço!"), HttpStatus.NOT_ACCEPTABLE);
        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id da empresa!"), HttpStatus.NOT_ACCEPTABLE);

        Preco precoPrecoDelete = this.precoRepository.findById(idPreco).orElse(null);
        List<Preco> precoEmpresaDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoPrecoDelete == null)
            return new ResponseEntity(String.format("Id do preço %s informado não existe!",
                    idPreco.toUpperCase()), HttpStatus.NOT_FOUND);
        else if(precoEmpresaDelete.isEmpty())
            return new ResponseEntity(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()), HttpStatus.NOT_FOUND);


        for(Preco precoEmpresa : precoEmpresaDelete){
            if(precoEmpresa.getId().equals(precoPrecoDelete.getId())){
                this.precoRepository.deleteById(precoEmpresa.getId().toUpperCase());
            }
        }

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoEmpresaDelete.get(0).getProduto().getNome().toUpperCase(),
                precoEmpresaDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoPrecoDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdProEmp(String idProduto,String idEmpresa)  {

        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id do produto!"), HttpStatus.NOT_ACCEPTABLE);

        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id da empresa!"), HttpStatus.NOT_ACCEPTABLE);


        List<Preco> precoProdutoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());
        List<Preco> precoEmpresaDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoProdutoDelete.isEmpty())
            return new ResponseEntity(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()), HttpStatus.NOT_FOUND);
        else if(precoEmpresaDelete.isEmpty())
            return new ResponseEntity(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()), HttpStatus.NOT_FOUND);


        for(Preco precoProduto : precoProdutoDelete){
            for(Preco precoEmpresa : precoEmpresaDelete){
                if(precoProduto.getId().equals(precoEmpresa.getId())){
                    this.precoRepository.deleteById(precoProduto.getId().toUpperCase());
                }
            }
        }

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoProdutoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getId().toUpperCase()), HttpStatus.CREATED);
    }
    public ResponseEntity deletarPrecoToIdPreProEmp(String idPreco,String idProduto,String idEmpresa)  {

        if(idPreco.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id do preço!"), HttpStatus.NOT_ACCEPTABLE);
        if(idProduto.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id do produto!"), HttpStatus.NOT_ACCEPTABLE);
        if(idEmpresa.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Informe o id da empresa!"), HttpStatus.NOT_ACCEPTABLE);

        Preco precoPrecoDelete = this.precoRepository.findById(idPreco).orElse(null);
        List<Preco> precoProdutoDelete =  this.precoRepository.findAllByProdutoId(idProduto.toUpperCase());
        List<Preco> precoEmpresaDelete =  this.precoRepository.findAllByEmpresaId(idEmpresa.toUpperCase());

        if(precoPrecoDelete == null)
            return new ResponseEntity(String.format("Id do preço %s informado não existe!",
                    idPreco.toUpperCase()), HttpStatus.NOT_FOUND);
        else if(precoProdutoDelete.isEmpty())
            return new ResponseEntity(String.format("Id do produto %s informado não existe!",
                    idProduto.toUpperCase()), HttpStatus.NOT_FOUND);
        else if(precoEmpresaDelete.isEmpty())
            return new ResponseEntity(String.format("Id do empresa %s informado não existe!",
                    idEmpresa.toUpperCase()), HttpStatus.NOT_FOUND);


        for(Preco precoProduto : precoProdutoDelete){
            for(Preco precoEmpresa : precoEmpresaDelete){
                if(precoProduto.getId().equals(precoEmpresa.getId())){
                    if(precoProduto.getId().equals(precoPrecoDelete.getId())){
                        this.precoRepository.deleteById(precoProduto.getId().toUpperCase());
                    }
                }
            }
        }

        return new ResponseEntity(String.format("Preço do produto %s para empresa %s deletado com sucesso ID(%s)!",
                precoProdutoDelete.get(0).getProduto().getNome().toUpperCase(),
                precoProdutoDelete.get(0).getEmpresa().getNome().toUpperCase(),
                precoPrecoDelete.getId().toUpperCase()), HttpStatus.CREATED);
    }

}
