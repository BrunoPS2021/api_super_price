package com.app.restApiAndroid.services.servicesvalidates.apiExterna;

import com.app.restApiAndroid.models.apisExternas.empresa.EmpresaApiExterna;
import com.app.restApiAndroid.models.empresa.Empresa;
import org.javatuples.Triplet;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.Normalizer;

@Service
public class EmpresasApiExternaService {
    public Triplet<RestTemplate, UriComponents, HttpEntity> resultQueryEmpresaApiExterna(String query, String empresa){
        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("www.receitaws.com.br")
                .path(String.format("%s%s",query,empresa))
                .build();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(headers);

        return Triplet.with(template,uri,entity);
    }
    public Empresa empresa(EmpresaApiExterna empresaSalvar){
        if(empresaSalvar.status.equals("ERROR"))
            return null;

        Empresa  empresaNew = new Empresa(
                removerAcentosEspacoBranco(empresaSalvar.cnpj),
                removerAcentosEspacoBranco(empresaSalvar.nome),
                removerAcentosEspacoBranco(empresaSalvar.fantasia),
                removerAcentosEspacoBranco(empresaSalvar.uf),
                removerAcentosEspacoBranco(empresaSalvar.logradouro),
                removerAcentosEspacoBranco(empresaSalvar.numero),
                removerAcentosEspacoBranco(empresaSalvar.bairro),
                removerAcentosEspacoBranco(empresaSalvar.municipio),
                removerAcentosEspacoBranco(empresaSalvar.cep),
                removerAcentosEspacoBranco(empresaSalvar.complemento)
                );

        return empresaNew;
    }
    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
