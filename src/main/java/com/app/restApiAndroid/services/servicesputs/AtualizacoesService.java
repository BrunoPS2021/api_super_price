package com.app.restApiAndroid.services.servicesputs;

import com.app.restApiAndroid.models.dots.PrecoDTO;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.preco.Preco;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.models.usuario.DadosUsuario;
import com.app.restApiAndroid.models.usuario.Usuario;
import com.app.restApiAndroid.models.dots.RegisterDTO;
import com.app.restApiAndroid.models.dots.ValidationsDTO;
import com.app.restApiAndroid.repositories.*;
import com.app.restApiAndroid.services.servicesvalidates.empresa.ValidateInfoEmpresaService;
import com.app.restApiAndroid.services.servicesvalidates.preco.ValidateInfoPrecoService;
import com.app.restApiAndroid.services.servicesvalidates.produto.ValidateInfoProdutoService;
import com.app.restApiAndroid.services.servicesvalidates.usuario.ValidateInfoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class AtualizacoesService {

    //usuario
    @Autowired
    private DadosUsuarioRepository dadosUsuarioRepository;
    @Autowired
    private ValidateInfoUsuarioService validateInfoUsuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public ResponseEntity atualizarUserToId(String id,RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoUsuarioService.validInformations(dadosUsuarioRepository, usuarioRepository,data,null,3,id,null);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        Usuario newUser = new Usuario(data.login().toUpperCase(),
                encryptedPassword, data.role());

        DadosUsuario newDadosUsuario = new DadosUsuario(data.dadosUsuario().getName(),
                data.dadosUsuario().getEmail(), data.dadosUsuario().getNascimento(),
                newUser,data.dadosUsuario().getEnderecos(),
                data.dadosUsuario().getContatos());

        DadosUsuario dadosUsuarioOld = this.dadosUsuarioRepository.findByUserId(id);

        usuarioRepository.deleteById(dadosUsuarioOld.getUser().getId().toUpperCase());

        String idAlterado = this.dadosUsuarioRepository.save(newDadosUsuario).getId().toUpperCase();

        return new ResponseEntity(String.format("Usuário %s alterado com sucesso ID(%s)!",data.dadosUsuario().getName().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarUserToLogin(String login,RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        if(login.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Login não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"),HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoUsuarioService.validInformations(dadosUsuarioRepository, usuarioRepository,data,null,4,null,login);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());


        Usuario newUser = new Usuario(data.login().toUpperCase(),
                encryptedPassword, data.role());

        DadosUsuario newDadosUsuario = new DadosUsuario(data.dadosUsuario().getName(),
                data.dadosUsuario().getEmail(),data.dadosUsuario().getNascimento(),
                newUser,data.dadosUsuario().getEnderecos(),
                data.dadosUsuario().getContatos());

        DadosUsuario dadosUsuarioOld = this.dadosUsuarioRepository.findByUserLogin(login.toUpperCase());

        usuarioRepository.deleteById(dadosUsuarioOld.getUser().getId().toUpperCase());

        String idAlterado = this.dadosUsuarioRepository.save(newDadosUsuario).getId().toUpperCase();

        return new ResponseEntity(String.format("Usuário %s alterado com sucesso ID(%s)!",data.dadosUsuario().getName(),idAlterado), HttpStatus.NO_CONTENT);
    }

    //produto
    @Autowired
    private ValidateInfoProdutoService validateInfoProdutoService;
    @Autowired
    private ProdutoRepository produtoRepository;
    public ResponseEntity atualizarProdutoToId(String id, Produto data) {

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoProdutoService.validInformations(produtoRepository,data,2,id,null);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Produto produto = produtoRepository.findById(id.toUpperCase()).orElse(null);

        this.produtoRepository.deleteById(produto.getId().toUpperCase());
        String idAlterado = this.produtoRepository.save(data).getId().toUpperCase();

        return new ResponseEntity(String.format("Produto %s alterado com sucesso ID(%s)!",produto.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarProdutoToGtin(String gtin,Produto data) {

        if(gtin.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Login não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"),HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoProdutoService.validInformations(produtoRepository,data,3,null,gtin);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());


        Produto produto = produtoRepository.findByGtin(gtin.toUpperCase());

        this.produtoRepository.deleteById(produto.getId().toUpperCase());
        String idAlterado = this.produtoRepository.save(data).getId().toUpperCase();

        return new ResponseEntity(String.format("Produto %s alterado com sucesso ID(%s)!",produto.getNome(),idAlterado), HttpStatus.NO_CONTENT);
    }

    //empresa
    @Autowired
    private ValidateInfoEmpresaService validateInfoEmpresaService;
    @Autowired
    private EmpresaRepository empresaRepository;
    public ResponseEntity atualizarEmpresaToId(String id, Empresa data) {

        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoEmpresaService.validInformations(empresaRepository,data,2,id);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Empresa empresa = empresaRepository.findById(id.toUpperCase()).orElse(null);

        this.empresaRepository.deleteById(empresa.getId().toUpperCase());
        String idAlterado = this.empresaRepository.save(data).getId().toUpperCase();

        return new ResponseEntity(String.format("Empresa %s alterado com sucesso ID(%s)!",empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarEmpresaToCnpj(String cnpj, Empresa data) {

        if(cnpj.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Cnpj não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoEmpresaService.validInformations(empresaRepository,data,3,cnpj);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Empresa empresa = empresaRepository.findByCnpj(cnpj.toUpperCase());

        this.empresaRepository.deleteById(empresa.getId().toUpperCase());
        String idAlterado = this.empresaRepository.save(data).getId().toUpperCase();

        return new ResponseEntity(String.format("Empresa %s alterado com sucesso ID(%s)!",empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarEmpresaToNome(String nome, Empresa data) {

        if(nome.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Nome não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoEmpresaService.validInformations(empresaRepository,data,4,nome);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Empresa empresa = empresaRepository.findByNome(nome.toUpperCase());

        this.empresaRepository.deleteById(empresa.getId().toUpperCase());
        String idAlterado = this.empresaRepository.save(data).getId().toUpperCase();

        return new ResponseEntity(String.format("Empresa %s alterado com sucesso ID(%s)!",empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarEmpresaToFantasia(String fantasia, Empresa data) {

        if(fantasia.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Fantasia não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        ValidationsDTO info = validateInfoEmpresaService.validInformations(empresaRepository,data,5,fantasia);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Empresa empresa = empresaRepository.findByFantasia(fantasia.toUpperCase());

        this.empresaRepository.deleteById(empresa.getId().toUpperCase());
        String idAlterado = this.empresaRepository.save(data).getId().toUpperCase();

        return new ResponseEntity(String.format("Empresa %s alterado com sucesso ID(%s)!",empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }

    //preco
    @Autowired
    private ValidateInfoPrecoService validateInfoPrecoService;
    @Autowired
    private PrecoRepository precoRepository;
    public ResponseEntity atualizarPrecoToId(String id, PrecoDTO data){
        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        DadosUsuario usuario = this.dadosUsuarioRepository.findById(data.usuario().getId().replace(" ","")).orElse(null);
        Produto produto = this.produtoRepository.findById(data.produto().getId().replace(" ","")).orElse(null);
        Empresa empresa = this.empresaRepository.findById(data.empresa().getId().replace(" ","")).orElse(null);


        ValidationsDTO info = validateInfoPrecoService.validInformations(precoRepository,
                usuario,produto,empresa,data,2,id,null,null,null,null);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Preco precoNew = new Preco(new Date(),data.preco(),usuario,produto,empresa,data.ativo());

        String idAlterado = this.precoRepository.save(precoNew).getId().toUpperCase();

        return new ResponseEntity(String.format("Preço do produto %s da empresa %s alterado com sucesso ID(%s)!",
                produto.getNome(),empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarPrecoToIdProEmp(String idPro,String idEmp, PrecoDTO data){
        if(idPro.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do produto não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(idEmp.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id da empresa não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        DadosUsuario usuario = this.dadosUsuarioRepository.findById(data.usuario().getId().replace(" ","")).orElse(null);
        Produto produto = this.produtoRepository.findById(data.produto().getId().replace(" ","")).orElse(null);
        Empresa empresa = this.empresaRepository.findById(data.empresa().getId().replace(" ","")).orElse(null);

        Produto produtoInformado = this.produtoRepository.findById(idPro.toUpperCase().replace(" ","")).orElse(null);
        Empresa empresaInformado = this.empresaRepository.findById(idEmp.replace(" ","")).orElse(null);


        ValidationsDTO info = validateInfoPrecoService.validInformations(precoRepository,
                usuario,produto,empresa,data,3,idPro,idEmp,produtoInformado,empresaInformado,null);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Preco precoNew = new Preco(new Date(),data.preco(),usuario,produto,empresa,data.ativo());

        String idAlterado = this.precoRepository.save(precoNew).getId().toUpperCase();

        return new ResponseEntity(String.format("Preço do produto %s da empresa %s alterado com sucesso ID(%s)!",
                produto.getNome(),empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
    public ResponseEntity atualizarPrecoToIdPreProEmp(String id,String idPro,String idEmp, PrecoDTO data){
        if(id.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do preço não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(idPro.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id do produto não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);

        if(idEmp.replace(" ","").isEmpty())
            return new ResponseEntity(String.format("Id da empresa não pode ser vazio"),HttpStatus.NOT_ACCEPTABLE);


        if(data == null)
            return new ResponseEntity(String.format("Informe os dados no formato Json"), HttpStatus.NOT_ACCEPTABLE);

        DadosUsuario usuario = this.dadosUsuarioRepository.findById(data.usuario().getId().replace(" ","")).orElse(null);
        Produto produto = this.produtoRepository.findById(data.produto().getId().replace(" ","")).orElse(null);
        Empresa empresa = this.empresaRepository.findById(data.empresa().getId().replace(" ","")).orElse(null);

        Produto produtoInformado = this.produtoRepository.findById(idPro.toUpperCase().replace(" ","")).orElse(null);
        Empresa empresaInformado = this.empresaRepository.findById(idEmp.replace(" ","")).orElse(null);


        ValidationsDTO info = validateInfoPrecoService.validInformations(precoRepository,
                usuario,produto,empresa,data,4,idPro,idEmp,produtoInformado,empresaInformado,id);

        if(!info.valid())
            return new ResponseEntity(info.message(),info.statusCode());

        Preco precoNew = new Preco(new Date(),data.preco(),usuario,produto,empresa,data.ativo());

        String idAlterado = this.precoRepository.save(precoNew).getId().toUpperCase();

        return new ResponseEntity(String.format("Preço do produto %s da empresa %s alterado com sucesso ID(%s)!",
                produto.getNome(),empresa.getNome().toUpperCase(),idAlterado), HttpStatus.NO_CONTENT);
    }
}
