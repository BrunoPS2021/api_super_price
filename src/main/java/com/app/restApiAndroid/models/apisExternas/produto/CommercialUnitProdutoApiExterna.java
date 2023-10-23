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
public class CommercialUnitProdutoApiExterna {
    @JsonProperty("type_packaging")
    public String type_packaging;
    @JsonProperty("quantity_packaging")
    public String quantity_packaging;
    @JsonProperty("ballast")
    public String ballast;
    @JsonProperty("layer")
    public  String layer;
}
