package com.app.restApiAndroid.services.servicesvalidates.produto;

import com.app.restApiAndroid.models.produto.Produto;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.text.Normalizer;

@Service
public class ValidateDatasProdutoService {
    public Pair<Boolean, String> validateDatasUpdateProduto(Produto data){

        Pair<Boolean, String> validateDataProduto = validateDatasProduto(data);
        if (!validateDataProduto.getValue0())
            return validateDataProduto;

        Pair<Boolean, String> validateDataProdutoFormatoNumerico = validateDatasProdutoFormatoNumerico(data);
        if (!validateDataProdutoFormatoNumerico.getValue0())
            return validateDataProdutoFormatoNumerico;

        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasCreateProduto(Produto data){

        Pair<Boolean, String> validateDataProduto = validateDatasProduto(data);
        if (!validateDataProduto.getValue0())
            return validateDataProduto;

        Pair<Boolean, String> validateDataProdutoFormatoNumerico = validateDatasProdutoFormatoNumerico(data);
        if (!validateDataProdutoFormatoNumerico.getValue0())
            return validateDataProdutoFormatoNumerico;

        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasProduto(Produto data) {
        if (data.getNome().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo nome não pode esta vazio!"));
        if (data.getGtin().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo gtin não pode esta vazio!"));

        return Pair.with(true, "");

    }
    public Pair<Boolean, String> validateDatasProdutoFormatoNumerico(Produto data) {
        if (!StringUtils.isNumeric(data.getQuantidadeEmbalagem()))
            return Pair.with(false, String.format("Campo Quantidade Embalagem só aceita números (%s)!", data.getQuantidadeEmbalagem()));
        if (!StringUtils.isNumeric(data.getGtin()))
            return Pair.with(false, String.format("Campo código de barras só aceita números (%s)!", data.getGtin()));

        return Pair.with(true, "");

    }
    private void upperCaseData(Produto data){
        data.setNome(removerAcentosEspacoBranco(data.getNome().toUpperCase()));
        data.setMarcaNome(removerAcentosEspacoBranco(data.getMarcaNome().toUpperCase()));
        data.setTipoProduto(!data.getTipoProduto().isEmpty()?"OUTROS":
                data.getTipoProduto() == null ? "OUTROS":removerAcentosEspacoBranco(data.getTipoProduto().toUpperCase()));
        data.setDescricao(removerAcentosEspacoBranco(data.getDescricao().toUpperCase()));
        data.setTipoEmbalagem(removerAcentosEspacoBranco(data.getTipoEmbalagem().toUpperCase()));
    }
    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
