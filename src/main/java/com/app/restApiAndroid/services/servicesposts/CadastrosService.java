package com.app.restApiAndroid.services.servicesposts;

import com.app.restApiAndroid.models.Mensagem;
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
import org.springframework.http.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
public class CadastrosService {

    //usuario
    @Autowired
    private DadosUsuarioRepository dadosUsuarioRepository;
    @Autowired
    private ValidateInfoUsuarioService validateInfoUsuarioService;
    @Autowired
    private UsuarioRepository usuarioRepository;
    public ResponseEntity usuario(RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Mensagem mensagem = new Mensagem();

        if(data == null) {
            mensagem.setMessage(String.format("Informe os dados no formato Json"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        ValidationsDTO info = validateInfoUsuarioService.validInformations(dadosUsuarioRepository, usuarioRepository,data,null,1,null,null);

        if(!info.valid()) {
            mensagem.setMessage(info.message());
            return new ResponseEntity<>(mensagem, info.statusCode());
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());


        Usuario newUser = new Usuario(data.login().toUpperCase(),
                encryptedPassword, data.role());

        DadosUsuario newDadosUsuario = new DadosUsuario(data.dadosUsuario().getName(),
                data.dadosUsuario().getEmail(),data.dadosUsuario().getNascimento()
                ,newUser,data.dadosUsuario().getEnderecos(),
                data.dadosUsuario().getContatos());

        String id = this.dadosUsuarioRepository.save(newDadosUsuario).getId();

        mensagem.setMessage(String.format("Usuário %s cadastrado com sucesso ID(%s)!",data.dadosUsuario().getName().toUpperCase(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    //produto
    @Autowired
    private ValidateInfoProdutoService validateInfoProdutoService;
    @Autowired
    private ProdutoRepository produtoRepository;
    public ResponseEntity produto(Produto data) {
        Mensagem mensagem = new Mensagem();

        if(data == null) {
            mensagem.setMessage(String.format("Informe os dados no formato Json"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        ValidationsDTO info = validateInfoProdutoService.validInformations(produtoRepository,data,1,null,null);

        if(!info.valid()) {
            mensagem.setMessage(info.message());
            return new ResponseEntity<>(mensagem, info.statusCode());
        }
        String id = this.produtoRepository.save(data).getId();

        mensagem.setMessage(String.format("Produto %s cadastrado com sucesso ID(%s)!",data.getNome().toUpperCase(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    //empresa
    @Autowired
    private ValidateInfoEmpresaService validateInfoEmpresaService;
    @Autowired
    private EmpresaRepository empresaRepository;
    public ResponseEntity empresa(Empresa data){
        Mensagem mensagem = new Mensagem();

        if(data == null) {
            mensagem.setMessage(String.format("Informe os dados no formato Json"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        ValidationsDTO info = validateInfoEmpresaService.validInformations(empresaRepository,data,1,null);

        if(!info.valid()) {
            mensagem.setMessage(info.message());
            return new ResponseEntity<>(mensagem, info.statusCode());
        }
        String id = this.empresaRepository.save(data).getId();

        mensagem.setMessage(String.format("Empesa %s cadastrado com sucesso ID(%s)!",data.getNome().toUpperCase(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }

    //preco
    @Autowired
    private PrecoRepository precoRepository;
    @Autowired
    private ValidateInfoPrecoService validateInfoPrecoService;
    public ResponseEntity preco(PrecoDTO data) {
        Mensagem mensagem = new Mensagem();

        if(data == null) {
            mensagem.setMessage(String.format("Informe os dados no formato Json"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        DadosUsuario usuario = this.dadosUsuarioRepository.findById(data.usuario().getId().replace(" ","")).orElse(null);
        Produto produto = this.produtoRepository.findById(data.produto().getId().replace(" ","")).orElse(null);
        Empresa empresa = this.empresaRepository.findById(data.empresa().getId().replace(" ","")).orElse(null);

        ValidationsDTO info = validateInfoPrecoService.validInformations(precoRepository,usuario,produto,
                empresa,data,1,null,null,null,null,null);

        if(!info.valid()) {
            mensagem.setMessage(info.message());
            return new ResponseEntity<>(mensagem, info.statusCode());
        }

        Preco precoNew = new Preco(new Date(),data.preco(),usuario,produto,empresa,
                data.ativo());

        String id = this.precoRepository.save(precoNew).getId();

        mensagem.setMessage(String.format("Preço para produto %s na empresa %s cadastrado com sucesso ID(%s)!",
                produto.getNome().toUpperCase(),empresa.getNome(),id.toUpperCase()));
        return new ResponseEntity<>(mensagem, HttpStatus.CREATED);
    }


}
