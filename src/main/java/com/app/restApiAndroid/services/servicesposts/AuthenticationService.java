package com.app.restApiAndroid.services.servicesposts;

import com.app.restApiAndroid.infra.security.TokenService;
import com.app.restApiAndroid.models.Mensagem;
import com.app.restApiAndroid.models.dots.AuthenticationDTO;
import com.app.restApiAndroid.models.dots.LoginResponseDTO;
import com.app.restApiAndroid.models.usuario.Usuario;
import com.app.restApiAndroid.models.dots.ValidationsDTO;
import com.app.restApiAndroid.repositories.UsuarioRepository;
import com.app.restApiAndroid.services.servicesvalidates.usuario.ValidateInfoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticationService {

    //usuario
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private ValidateInfoUsuarioService validateInfoUsuarioService;
    public ResponseEntity login(AuthenticationDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        Mensagem mensagem = new Mensagem();
        if(data == null) {
            mensagem.setMessage(String.format("Informe os dados no formato Json"));
            return new ResponseEntity<>(mensagem, HttpStatus.NOT_ACCEPTABLE);
        }
        ValidationsDTO info = validateInfoUsuarioService.validInformations(null, usuarioRepository,null,data,2,null,null);

        if(!info.valid()) {
            mensagem.setMessage(info.message());
            return new ResponseEntity<>(mensagem, info.statusCode());
        }
        var userNamePassword = new UsernamePasswordAuthenticationToken(data.login().toUpperCase(),data.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = this.tokenService.generateToken((Usuario) auth.getPrincipal());


        return new ResponseEntity<>(new LoginResponseDTO(token), HttpStatus.OK);
    }

}
