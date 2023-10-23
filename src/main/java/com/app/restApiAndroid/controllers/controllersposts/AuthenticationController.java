package com.app.restApiAndroid.controllers.controllersposts;

import com.app.restApiAndroid.models.dots.AuthenticationDTO;
import com.app.restApiAndroid.services.servicesposts.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@Tag(name = "Autenticações", description = "Controller referente a Autenticações")
@RequestMapping(value = "${route.principal}")
public class AuthenticationController {
    @Autowired()
    AuthenticationService authenticationService;

    //usuario
    @Operation(summary = "Retorna token do usuário logado do app super price",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Usuário logado com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Senha Incorreta!",
                            content = @Content(examples = @ExampleObject(value = "Senha Incorreta!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Usuário não encontrado!")))
            })
    @PostMapping(value = "${route.autenticacaoLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity login(@RequestBody @Validated AuthenticationDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return authenticationService.login(data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
