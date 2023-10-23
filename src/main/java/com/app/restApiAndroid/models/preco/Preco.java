package com.app.restApiAndroid.models.preco;

import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.models.usuario.DadosUsuario;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;
import java.util.Date;

@Table(name = "prices")
@Entity(name = "prices")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Preco {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Temporal(TemporalType.DATE)
    Date ultimaAtualizacao;

    private boolean ativo;

    @NumberFormat(style = NumberFormat.Style.NUMBER, pattern = "#.###,##")
    private BigDecimal precoProdutoOfertado;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "usersId", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    DadosUsuario usuario;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "productsId", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Produto produto;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "businessId", referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    Empresa empresa;

    public Preco(Date ultimaAtualizacao,BigDecimal precoProdutoOfertado,DadosUsuario usuario,Produto produto,
                 Empresa empresa,boolean ativo){
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.precoProdutoOfertado = precoProdutoOfertado;
        this.usuario = usuario;
        this.produto = produto;
        this.empresa = empresa;
        this.ativo = ativo;
    }
}
