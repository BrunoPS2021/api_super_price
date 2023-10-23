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
public class NcmProdutoApiExterna {
    @JsonProperty("code")
    public String code;
    @JsonProperty("description")
    public String description;
    @JsonProperty("full_description")
    public String fullDescription;
    @JsonProperty("ex")
    public String ex;
}
