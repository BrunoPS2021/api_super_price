package com.app.restApiAndroid.controllers.controllersputs;

import com.app.restApiAndroid.models.dots.PrecoDTO;
import com.app.restApiAndroid.models.dots.RegisterDTO;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.services.servicesputs.AtualizacoesService;
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
@Tag(name = "Atualizações", description = "Controller referente a Atualizações")
@RequestMapping(value = "${route.principal}")
public class AtualizacoesController {
    @Autowired
    AtualizacoesService atualizacoesService;

    //usuario
    @Operation(summary = "Atualizar o usuário pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Usuário atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Usuário não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaUsuarioToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarUserToId(@RequestParam String id, @RequestBody @Validated RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarUserToId(id, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar o usuário pelo nome do login app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Usuário atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Usuário não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaUsuarioToLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarUserToLogin(@RequestParam String login, @RequestBody @Validated RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarUserToLogin(login, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //produto
    @Operation(summary = "Atualizar o produto pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Produto atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Produto não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaProdutoToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarProdutoToId(@RequestParam String id, @RequestBody @Validated Produto data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarProdutoToId(id, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar o produto pelo código de barras app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Produto atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Produto não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaProdutoToGtin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarProdutoToGtin(@RequestParam String gtin, @RequestBody @Validated Produto data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarProdutoToGtin(gtin, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //empresa
    @Operation(summary = "Atualizar empresa pelo id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaEmpresaToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarEmpresaToId(@RequestParam String id, @RequestBody @Validated Empresa data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarEmpresaToId(id, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar empresa pelo cnpj app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaEmpresaToCnpj}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarEmpresaToCnpj(@RequestParam String cnpj, @RequestBody @Validated Empresa data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarEmpresaToCnpj(cnpj, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar empresa pelo nome app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaEmpresaToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarEmpresaToNome(@RequestParam String nome, @RequestBody @Validated Empresa data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarEmpresaToNome(nome, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar empresa pelo nome fantasia app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaEmpresaToFantasia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarEmpresaToFantasia(@RequestParam String fantasia, @RequestBody @Validated Empresa data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarEmpresaToFantasia(fantasia, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //preco
    @Operation(summary = "Atualizar o preço pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaPrecoToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarUserToId(@RequestParam String id, @RequestBody @Validated PrecoDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarPrecoToId(id, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar o preço pelo Id do produto e Id da empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaPrecoToIdProEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarPrecoToIdProEmp(@RequestParam String idProduto,@RequestParam String idEmpresa, @RequestBody @Validated PrecoDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarPrecoToIdProEmp(idProduto,idEmpresa, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Atualizar o preço pelo Id do preço, Id do produto e Id da empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço atualizado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço atualizado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @PutMapping(value = "${route.atualizaPrecoToIdPreProEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity atualizarPrecoToIdPreProEmp(@RequestParam String idPreco,@RequestParam String idProduto,@RequestParam String idEmpresa, @RequestBody @Validated PrecoDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return atualizacoesService.atualizarPrecoToIdPreProEmp(idPreco,idProduto,idEmpresa, data);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
