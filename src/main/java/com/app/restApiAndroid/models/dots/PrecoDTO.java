package com.app.restApiAndroid.models.dots;

import com.app.restApiAndroid.models.GeralJson;

import java.math.BigDecimal;

public record PrecoDTO(BigDecimal preco, GeralJson usuario, GeralJson produto, GeralJson empresa,boolean ativo) {

}
