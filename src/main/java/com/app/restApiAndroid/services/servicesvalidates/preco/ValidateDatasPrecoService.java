package com.app.restApiAndroid.services.servicesvalidates.preco;

import com.app.restApiAndroid.models.dots.PrecoDTO;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
@Service
public class ValidateDatasPrecoService {
    public Pair<Boolean, String> validateDatasUpdateProduto(PrecoDTO data){

        Pair<Boolean, String> validateDataProduto = validateDatasPreco(data);
        if (!validateDataProduto.getValue0())
            return validateDataProduto;

        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasCreateProduto(PrecoDTO data){

        Pair<Boolean, String> validateDataProduto = validateDatasPreco(data);
        if (!validateDataProduto.getValue0())
            return validateDataProduto;

        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasPreco(PrecoDTO data) {
        if (data.preco().toString().replace(" ","").isEmpty())
            return Pair.with(false, String.format("Campo preço não pode esta vazio!"));
        if (data.usuario().getId().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo id do usuário não pode esta vazio!"));
        if (data.produto().getId().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo id do produto não pode esta vazio!"));
        if (data.empresa().getId().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo id da empresa não pode esta vazio!"));

        return Pair.with(true, "");

    }
    private void upperCaseData(PrecoDTO data){
        data.usuario().setId(data.usuario().getId().toUpperCase());
        data.produto().setId(data.produto().getId().toUpperCase());
        data.empresa().setId(data.empresa().getId().toUpperCase());
    }
    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
