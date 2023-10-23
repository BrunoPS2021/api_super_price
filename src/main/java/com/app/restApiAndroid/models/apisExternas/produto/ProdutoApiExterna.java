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
public class ProdutoApiExterna {
    @JsonProperty("gtin")
    public String gtin;
    @JsonProperty("description")
    public String description;
    @JsonProperty("min_price")
	public String minPrice;
    @JsonProperty("max_price")
    public String maxPrice;
    @JsonProperty("avg_price")
    public String avgPrice;
    @JsonProperty("brand")
    public BrandProdutoApiExterna brand;
    @JsonProperty("gpc")
    public GpcProdutoApiExterna gpc;
    @JsonProperty("ncm")
    public NcmProdutoApiExterna ncm;
    @JsonProperty("cest")
    public CestProdutoApiExterna cest;
    @JsonProperty("gtins")
    public List<GtinsProdutoApiExterna> gtins;
}
