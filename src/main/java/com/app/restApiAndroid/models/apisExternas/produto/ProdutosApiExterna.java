package com.app.restApiAndroid.models.apisExternas.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutosApiExterna {
    @JsonProperty("products")
    public List<ProdutoApiExterna> products;
    @JsonProperty("current_page")
    public int pageAtual;
    @JsonProperty("per_page")
    public int pagePer;
    @JsonProperty("total_pages")
    public int totalPagina;
    @JsonProperty("total_count")
    public int totalProdutos;
}
