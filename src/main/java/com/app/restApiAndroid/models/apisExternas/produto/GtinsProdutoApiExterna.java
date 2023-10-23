package com.app.restApiAndroid.models.apisExternas.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GtinsProdutoApiExterna {
    @JsonProperty("gtin")
    public String gtin;
    @JsonProperty("commercial_unit")
    public CommercialUnitProdutoApiExterna commercial_unit;
}
