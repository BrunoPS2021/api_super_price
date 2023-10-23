package com.app.restApiAndroid.models.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "addressusers")
@Entity(name = "addressusers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String tipoLogradouro;
    private String logradouro;
    private String bairro;
    private String numero;
    private String complemento;
    private String pontoReferencia;
    private String cidade;
    private String uf;
    private String cep;
    private boolean principal;

}
