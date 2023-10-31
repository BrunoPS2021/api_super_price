package com.app.restApiAndroid.controllers.controllersgets;

import com.app.restApiAndroid.models.Mensagem;
import com.app.restApiAndroid.services.servicesgets.BuscasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Buscar", description = "Controller referente buscas")
@RequestMapping(value = "${route.principal}")
public class BuscasController {

    @Autowired
    BuscasService buscasService;

    //usuario
    @Operation(summary = "Buscar lista de usuários do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de usuários com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista usuários vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista usuários vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaUsuarios}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listUser() {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listUser();
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna a lista dos usuários especificado por nome do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado os usuários com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado os usuários com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O usuário não existe!",
                    content = @Content(examples = @ExampleObject(value = "O usuário não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaUsuarioToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listUserToName(@RequestParam String name) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listUserToName(name);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o usuario especificado por nome do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o usuário com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o usuário com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O usuário não existe!",
                    content = @Content(examples = @ExampleObject(value = "O usuário não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaUsuarioToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userToName(@RequestParam String nome) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.userToName(nome);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o usuário especificado por login do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o usuário com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontado o usuários com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O usuário não existe!",
                    content = @Content(examples = @ExampleObject(value = "O usuário não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaUsuarioToLogin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userToLogin(@RequestParam String login) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.userToLogin(login);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o usuário especificado por email do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o usuário com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontado o usuários com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O usuário não existe!",
                    content = @Content(examples = @ExampleObject(value = "O usuário não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaUsuarioToEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userToEmail(@RequestParam String email) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.userToEmail(email);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o usuário especificado por login ou email do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o usuário com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontado o usuários com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O usuário não existe!",
                    content = @Content(examples = @ExampleObject(value = "O usuário não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaUsuarioToLoginEmail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userToLoginEmail(@RequestParam String loginEmail) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.userToLoginEmail(loginEmail);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o usuário especificado por ID do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o usuário com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o usuários com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O usuário não existe!",
                    content = @Content(examples = @ExampleObject(value = "O usuário não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaUsuarioToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity userToId(@RequestParam String id) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.userToId(id);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //produto
    @Operation(summary = "Buscar lista de produtos do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de produtos com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista produtos vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista produtos vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaProdutos}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaProdutos() {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaProdutos();
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de produtos pelo tipo do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de produtos com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de produtos com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista produtos vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista produtos vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaProdutosToTipo}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaProdutosToTipo(@RequestParam String tipo) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaProdutosToTipo(tipo);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o produto especificado por nome do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o produto com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o produto com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O produto não existe!",
                    content = @Content(examples = @ExampleObject(value = "O produto não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaProdutoToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity produtoToNome(@RequestParam String nome) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.produtoToNome(nome);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna a lista dos produtos especificado por nome do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado os produtos com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado os produtos com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O produto não existe!",
                    content = @Content(examples = @ExampleObject(value = "O produto não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaProdutoToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaProdutoToNome(@RequestParam String nome) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaProdutoToNome(nome);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o produto especificado por ID do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o produto com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o produto com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O produto não existe!",
                    content = @Content(examples = @ExampleObject(value = "O produto não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaProdutoToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity produtoToId(@RequestParam String id) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.produtoToId(id);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o produto especificado pelo código de barras do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o produto com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o produto com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O produto não existe!",
                    content = @Content(examples = @ExampleObject(value = "O produto não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaProdutoToGtin}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity produtoToGtin(@RequestParam String gtin) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.produtoToGtin(gtin);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //produtoApiExterna
    @Operation(summary = "Busca produto api externa do app super price",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Encontrado o produto com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Encontrado o produto com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Erro com api externa!",
                            content = @Content(examples = @ExampleObject(value = "Erro com api externa!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Produto não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Produto não encontrado!")))
            })
    @GetMapping(value = "${route.buscaProdutoApiExterna}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscaProdutoApiExterna(@RequestParam(required = true) String gtin) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.buscaProdutoApiExterna(gtin);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //empresa
    @Operation(summary = "Buscar lista de empresas do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de empresas com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista empresas vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista empresas vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaEmpresas}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaEmpresas() {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaEmpresas();
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna a empresa especificado por ID do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrada a empresa com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrada a empresa com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "A empresa não existe!",
                    content = @Content(examples = @ExampleObject(value = "A Empresa não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaEmpresaToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity empresaToId(@RequestParam String id) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.empresaToId(id);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna a empresa especificado por cnpj do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrada a empresa com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrada a empresa com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "A empresa não existe!",
                    content = @Content(examples = @ExampleObject(value = "A Empresa não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaEmpresaToCnpj}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity empresaToCnpj(@RequestParam String cnpj) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.empresaToCnpj(cnpj);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna a empresa especificado por nome do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrada a empresa com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrada a empresa com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "A empresa não existe!",
                    content = @Content(examples = @ExampleObject(value = "A Empresa não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaEmpresaToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity empresaToNome(@RequestParam String nome) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.empresaToNome(nome);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna a empresa especificado por nome fantasia do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrada a empresa com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrada a empresa com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "A empresa não existe!",
                    content = @Content(examples = @ExampleObject(value = "A Empresa não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaEmpresaToFantasia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity empresaToFantasia(@RequestParam String fantasia) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.empresaToFantasia(fantasia);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de empresas pelo nome do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de empresas com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista empresas vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista empresas vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaEmpresaToNome}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaEmpresaToNome(@RequestParam String nome) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaEmpresaToNome(nome);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de empresas pelo nome fantasia do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de empresas com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista empresas vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista empresas vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaEmpresaToFantasia}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaEmpresaToFantasia(@RequestParam String fantasia) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaEmpresaToFantasia(fantasia);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de empresas pelo uf do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de empresas com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista empresas vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista empresas vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaEmpresaToUf}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaEmpresaToUf(@RequestParam String uf) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaEmpresaToUf(uf);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de empresas pelo municipio do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de empresas com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista empresas vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista empresas vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaEmpresaToMunicipio}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaEmpresaToMunicipio(@RequestParam String municipio) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaEmpresaToMunicipio(municipio);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de empresas pelo uf e municipio do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de empresas com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de empresas com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista empresas vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista empresas vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaEmpresaToUfMunicipio}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listaEmpresaToUfMunicipio(@RequestParam String uf,@RequestParam String municipio) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaEmpresaToUfMunicipio(uf,municipio);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //empresaApiExterna
    @Operation(summary = "Busca empresa api externa do app super price",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Encontrada a empresa com sucesso!",
                            content = @Content(examples = @ExampleObject(value = "Encontrada a empresa com sucesso!"))),
                    @ApiResponse(responseCode = "400", description = "Erro com api externa!",
                            content = @Content(examples = @ExampleObject(value = "Erro com api externa!"))),
                    @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!",
                            content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
                    @ApiResponse(responseCode = "404", description = "Empresa não encontrado!",
                            content = @Content(examples = @ExampleObject(value = "Empresa não encontrado!")))
            })
    @GetMapping(value = "${route.buscaEmpresaApiExterna}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity buscaEmpresaApiExterna(@RequestParam(required = true) String cnpj) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.buscaEmpresaApiExterna(cnpj);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //preco
    @Operation(summary = "Buscar lista de preços do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de preços com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de preços com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista preços vazia!",
                    content = @Content(examples = @ExampleObject(value = "Lista preços vazia!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaPrecos}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity listPreco() {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.listaPreco();
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o preço por ID do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "O preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaPrecoToId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToId(@RequestParam String id) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToId(id);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o preço por ID do produto do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "O preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaPrecoToIdPro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToIdPro(@RequestParam String idProduto) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToIdPro(idProduto);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Buscar lista de preço por ID da empresa do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "O preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaPrecoToIdEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToIdEmp(@RequestParam String idEmpresa) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToIdEmp(idEmpresa);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o preço por ID do preço e ID do produto do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "O preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaPrecoToIdPrePro}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToIdPrePro(@RequestParam String idPreco,@RequestParam String idProduto) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToIdPrePro(idPreco,idProduto);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o preço por ID do preço e ID da empresa do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "O preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaPrecoToIdPreEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToIdPreEmp(@RequestParam String idPreco,@RequestParam String idEmpresa) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToIdPreEmp(idPreco,idEmpresa);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna lista de preços por ID do produto e ID da empresa do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Lista de preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "Lista preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "Lista preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaListaPrecoToIdProEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToIdProEmp(@RequestParam String idProduto,@RequestParam String idEmpresa) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToIdProEmp(idProduto,idEmpresa);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @Operation(summary = "Retorna o preço por ID do preço, ID do Produto e ID da empresa do app super price")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Encontrado o preço com sucesso!",
                    content = @Content(examples = @ExampleObject(value = "Encontrado o preço com sucesso!"))),
            @ApiResponse(responseCode = "401", description = "Token não informado ou não autorizado!!",
                    content = @Content(examples = @ExampleObject(value = "Token não informado ou não autorizado!"))),
            @ApiResponse(responseCode = "404", description = "O preço não existe!",
                    content = @Content(examples = @ExampleObject(value = "O preço não existe!"))),
            @ApiResponse(responseCode = "406", description = "Campo com formato errado!",
                    content = @Content(examples = @ExampleObject(value = "Campo com formato errado!")))
    })
    @GetMapping(value = "${route.buscaPrecoToIdPreProEmp}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity precoToIdPreProEmp(@RequestParam String idPreco,@RequestParam String idProduto,@RequestParam String idEmpresa) {
        Mensagem mensagem = new Mensagem();
        try {
            return buscasService.precoToIdPreProEmp(idPreco,idProduto,idEmpresa);
        } catch (Exception erro) {
            mensagem.setMessage(String.format("Erro não mapeado: " + erro.getMessage()));
            return new ResponseEntity<>(mensagem, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
