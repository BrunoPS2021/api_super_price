package com.app.restApiAndroid.controllers.controllersdeletes;

import com.app.restApiAndroid.services.servicesdeletes.DeletesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
@Tag(name = "Deletes", description = "Controller referente a Deletes")
@RequestMapping(value = "${route.principal}")
public class DeletesController {
    @Autowired
    private DeletesService deletesService;
    //usuario
    @Operation(summary = "Deletar o usuário pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Usuário deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Usuário não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaUsuarioToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarUserToId(@RequestParam String id) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return deletesService.deletarUserToId(id);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o usuário pelo nome do login app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Usuário deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Usuário deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Usuário não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Usuário não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaUsuarioToLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarUserToLogin(@RequestParam String login) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        try {
            return deletesService.deletarUserToLogin(login);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //produto
    @Operation(summary = "Deletar o produto pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Produto deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Produto não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaProdutoToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarProdutoToId(@RequestParam String id) {
        try {
            return deletesService.deletarProdutoToId(id);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o produto pelo código de barras app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Produto deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Produto não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaProdutoToGtin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarProdutoToGtin(@RequestParam String gtin){
        try {
            return deletesService.deletarProdutoToGtin(gtin);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //empresa
    @Operation(summary = "Deletar o empresa pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaEmpresaToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarEmpresaToId(@RequestParam String id){
        try {
            return deletesService.deletarEmpresaToId(id);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o empresa pelo cnpj app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaEmpresaToCnpj}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarEmpresaToCnpj(@RequestParam String cnpj){
        try {
            return deletesService.deletarEmpresaToCnpj(cnpj);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o empresa pelo nome app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaEmpresaToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletaEmpresaToNome(@RequestParam String nome){
        try {
            return deletesService.deletarEmpresaToNome(nome);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o empresa pelo nome fantasia app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Empresa deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Empresa deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaEmpresaToFantasia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarEmpresaToFantasia(@RequestParam String fantasia){
        try {
            return deletesService.deletarEmpresaToFantasia(fantasia);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //preco
    @Operation(summary = "Deletar o preço pelo Id app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToId(@RequestParam String id){
        try {
            return deletesService.deletarPrecoToId(id);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o preço pelo Id produto app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToIdPro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToIdPro(@RequestParam String idProduto){
        try {
            return deletesService.deletarPrecoToIdPro(idProduto);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o preço pelo Id da empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToIdEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToIdEmp(@RequestParam String idEmpresa){
        try {
            return deletesService.deletarPrecoToIdEmp(idEmpresa);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o preço pelo Id produto e Id Empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToIdProEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToIdProEmp(@RequestParam String idProduto,@RequestParam String idEmpresa){
        try {
            return deletesService.deletarPrecoToIdProEmp(idProduto,idEmpresa);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o preço pelo Id preço e Id Empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToIdPreEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToIdPreEmp(@RequestParam String idPreco,@RequestParam String idEmpresa){
        try {
            return deletesService.deletarPrecoToIdPreEmp(idPreco,idEmpresa);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o preço pelo Id preço e Id produto app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToIdPrePro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToIdPrePro(@RequestParam String idPreco,@RequestParam String idProduto){
        try {
            return deletesService.deletarPrecoToIdPrePro(idPreco,idProduto);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Deletar o preço pelo Id do preço, Id produto e Id empresa app super price",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Preço deletado com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Preço deletado com sucesso!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Preço não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Preço não encontrado!"))),
                    @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                            content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
            })
    @DeleteMapping(value = "${route.deletaPrecoToIdPreProEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity deletarPrecoToIdPreProEmp(@RequestParam String idPreco,@RequestParam String idProduto,@RequestParam String idEmpresa){
        try {
            return deletesService.deletarPrecoToIdPreProEmp(idPreco,idProduto,idEmpresa);
        } catch (Exception erro) {
            return new ResponseEntity(String.format("Erro não mapeado: " + erro.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
