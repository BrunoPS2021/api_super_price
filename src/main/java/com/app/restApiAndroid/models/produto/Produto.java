package com.app.restApiAndroid.models.produto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Table(name = "products")
@Entity(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(unique=true)
    private String nome;
    private String marcaNome;
    private String tipoProduto;
    private String descricao;
    private String tipoEmbalagem;
    private String quantidadeEmbalagem;
    @Column(unique=true)
    private String gtin;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#.###,##")
    private BigDecimal precoMinimo;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#.###,##")
    private BigDecimal precoMaximo;
    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#.###,##")
    private BigDecimal precoMedio;
    @Lob @Basic(fetch = FetchType.LAZY)
    @Column(length=100000)
    private byte[] imagem;

    public Produto(String nome,String marcaNome,String tipoProduto,String descricao,
            String tipoEmbalagem,String quantidadeEmbalagem,String gtin,
            BigDecimal precoMinimo,BigDecimal precoMaximo,BigDecimal precoMedio,byte[] imagem){
        this.nome = nome;
        this.marcaNome = marcaNome;
        this.tipoProduto = tipoProduto;
        this.descricao = descricao;
        this.tipoEmbalagem = tipoEmbalagem;
        this.quantidadeEmbalagem = quantidadeEmbalagem;
        this.gtin = gtin;
        this.precoMinimo = precoMinimo;
        this.precoMaximo = precoMaximo;
        this.precoMedio = precoMedio;
        this.imagem = imagem;
    }
}
