package com.app.restApiAndroid.models.apisExternas.empresa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaApiExterna {

    @JsonProperty("status")
    public String status;
    @JsonProperty("cnpj")
    public String cnpj;
    @JsonProperty("nome")
    public String nome;
    @JsonProperty("fantasia")
    public String fantasia;
    @JsonProperty("logradouro")
    public String logradouro;
    @JsonProperty("numero")
    public String numero;
    @JsonProperty("municipio")
    public String municipio;
    @JsonProperty("bairro")
    public String bairro;
    @JsonProperty("uf")
    public String uf;
    @JsonProperty("cep")
    public String cep;
    @JsonProperty("complemento")
    public String complemento;
}
