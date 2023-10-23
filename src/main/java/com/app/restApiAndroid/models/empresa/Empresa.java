package com.app.restApiAndroid.models.empresa;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "business")
@Entity(name = "business")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String cnpj;
    private String nome;
    private String fantasia;
    private String uf;
    private String logradouro;
    private String numero;
    private String bairro;
    private String municipio;
    private String cep;
    private String complemento;

    public Empresa(String cnpj, String nome, String fantasia,String uf,String logradouro,String numero,
    String bairro,String municipio,String cep,String complemento){
         this.cnpj = cnpj;
         this.nome = nome;
         this.fantasia = fantasia;
         this.uf = uf;
         this.logradouro = logradouro;
         this.numero = numero;
         this.bairro = bairro;
         this.municipio = municipio;
         this.cep = cep;
         this.complemento = complemento;
    }
}
