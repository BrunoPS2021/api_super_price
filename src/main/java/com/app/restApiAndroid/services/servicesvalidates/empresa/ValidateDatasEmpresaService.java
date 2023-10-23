package com.app.restApiAndroid.services.servicesvalidates.empresa;

import com.app.restApiAndroid.models.empresa.Empresa;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
@Service
public class ValidateDatasEmpresaService {
    public Pair<Boolean, String> validateDatasUpdateEmpresa(Empresa data){

        Pair<Boolean, String> validateDataUser = validateDatasEmpresa(data);
        if (!validateDataUser.getValue0())
            return validateDataUser;

        Pair<Boolean, String> validateEndereco = validateDatasEndereco(data);
        if (!validateEndereco.getValue0())
            return validateEndereco;

        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasCreateEmpresa(Empresa data){

        Pair<Boolean, String> validateDataUser = validateDatasEmpresa(data);
        if (!validateDataUser.getValue0())
            return validateDataUser;

        Pair<Boolean, String> validateEndereco = validateDatasEndereco(data);
        if (!validateEndereco.getValue0())
            return validateEndereco;

        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasEmpresa(Empresa data) {

        if (data.getCnpj().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo cnpj não pode esta vazio!"));
        if(data.getCnpj().length() != 14)
            return Pair.with(false,String.format("Quantidade de caracteres do cnpj deve ser 14 e " +
                            "não deve conter pontuação, caracteres %d (%s)",
                    data.getCnpj().length(),data.getCnpj()));
        if (!StringUtils.isNumeric(data.getCnpj()))
            return Pair.with(false, String.format("Campo cnpj só aceita número (%s)!", data.getCnpj()));

        if (data.getNome().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo nome não pode esta vazio!"));
        if (data.getFantasia().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo nome fantasia não pode esta vazio!"));


        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasEndereco(Empresa data) {
            if (data.getLogradouro().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo logradouro não pode esta vazio!"));
            if (data.getMunicipio().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo municipio não pode esta vazio!"));
            if (data.getBairro().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo bairro não pode esta vazio!"));


        Pair<Boolean, String> fullDataEndNum = validateDatasFormatoNumeroEndereco(data);
        if (!fullDataEndNum.getValue0())
            return fullDataEndNum;

        Pair<Boolean, String> fullDataEndUf = validateDatasFormatoUF(data);
        if (!fullDataEndUf.getValue0())
            return fullDataEndUf;

        Pair<Boolean, String> fullDataEndCep = validateDatasFormatoCep(data);
        if (!fullDataEndCep.getValue0())
            return fullDataEndCep;

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasFormatoNumeroEndereco(Empresa data) {
            if (data.getNumero().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo número não pode esta vazio!"));
            if (!StringUtils.isNumeric(data.getNumero()) &&
                    !data.getNumero().toUpperCase().equals("S/N") &&
                    !data.getNumero().toUpperCase().equals("SN"))
                return Pair.with(false, String.format("Campo número só aceita números, s/n ou sn (%s)!",
                        data.getNumero()));

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasFormatoUF(Empresa data) {

            if (data.getUf().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo UF não pode esta vazio!"));
            if (data.getUf().length() != 2)
                return Pair.with(false, String.format("Campo UF com tamanho %d, o esperado é 2!",
                        data.getUf().length()));
            for (char caracter : data.getUf().toCharArray()) {
                if (StringUtils.isNumeric(Character.toString(caracter)))
                    return Pair.with(false, String.format("Campo UF só aceita carateres (%s)!", data.getUf()));

            }
        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasFormatoCep(Empresa data) {
            if (data.getCep().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo CEP não pode esta vazio!"));
            if (!StringUtils.isNumeric(data.getCep()))
                return Pair.with(false, String.format("Campo CEP só aceita números (%s)!", data.getCep()));
            if (data.getCep().length() != 8)
                return Pair.with(false, String.format("Campo CEP deve conter 8 números (%d)!",
                        data.getCep().length()));
            if(data.getCep().charAt(0) == '0')
                return Pair.with(false, String.format("Campo CEP não pode iniciar com zero (%d)!",
                        data.getCep()));

        return Pair.with(true, "");
    }
    private void upperCaseData(Empresa data){
        data.setCnpj(removerAcentosEspacoBranco(data.getCnpj().toUpperCase()));
        data.setNome(removerAcentosEspacoBranco(data.getNome().toUpperCase()));
        data.setFantasia(removerAcentosEspacoBranco(data.getFantasia().toUpperCase()));
        data.setUf(removerAcentosEspacoBranco(data.getUf().toUpperCase()));
        data.setLogradouro(removerAcentosEspacoBranco(data.getLogradouro().toUpperCase()));
        data.setNumero(removerAcentosEspacoBranco(data.getNumero().toUpperCase()));
        data.setBairro(removerAcentosEspacoBranco(data.getBairro().toUpperCase()));
        data.setMunicipio(removerAcentosEspacoBranco(data.getMunicipio().toUpperCase()));
        data.setCep(removerAcentosEspacoBranco(data.getCep().toUpperCase()));
        data.setComplemento(removerAcentosEspacoBranco(data.getComplemento().toUpperCase()));
        data.setBairro(removerAcentosEspacoBranco(data.getBairro()));

    }
    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
