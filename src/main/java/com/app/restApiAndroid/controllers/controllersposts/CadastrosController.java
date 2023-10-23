package com.app.restApiAndroid.controllers.controllersposts;

import com.app.restApiAndroid.models.dots.PrecoDTO;
import com.app.restApiAndroid.models.dots.RegisterDTO;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.services.servicesposts.CadastrosService;
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
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@Tag(name = "Cadastros", description = "Controller referente a Cadastros")
@RequestMapping(value = "${route.principal}")
public class CadastrosController {
    @Autowired
    CadastrosService cadastrosService;

    //usuario
    @Operation(summary = "Cadastra novo usuário app super price",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Usuário cadastrado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Usuário cadastrado com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado!",
                            content = @Content(examples = @ExampleObject(value = "Usuário já cadastrado!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PostMapping(value = "${route.cadastroUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity resgister(@RequestBody @Validated RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return cadastrosService.usuario(data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //produto
    @Operation(summary = "Cadastra novo produto app super price",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Produto cadastrado com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Produto já cadastrado!",
                            content = @Content(examples = @ExampleObject(value = "Produto já cadastrado!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PostMapping(value = "${route.cadastroProduto}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity produto(@RequestBody @Validated Produto data){
        try {
            return cadastrosService.produto(data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //empresa
    @Operation(summary = "Cadastra novo empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Empresa cadastrado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa cadastrado com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Empresa já cadastrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa já cadastrado!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PostMapping(value = "${route.cadastroEmpresa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity empresa(@RequestBody @Validated Empresa data){
        try {
            return cadastrosService.empresa(data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //preco
    @Operation(summary = "Cadastra novo preço app super price",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Preço cadastrado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço cadastrado com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Preço já cadastrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço já cadastrado!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PostMapping(value = "${route.cadastroPreco}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity preco(@RequestBody @Validated PrecoDTO data) {
        try {
            return cadastrosService.preco(data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
