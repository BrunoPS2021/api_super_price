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
public class GpcProdutoApiExterna {
    @JsonProperty("code")
    public String code;
    @JsonProperty("description")
    public String description;
}
