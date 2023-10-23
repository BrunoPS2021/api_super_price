package com.app.restApiAndroid.services.servicesvalidates.apiExterna;

import com.app.restApiAndroid.models.apisExternas.produto.ProdutoApiExterna;
import com.app.restApiAndroid.models.produto.Produto;
import org.javatuples.Triplet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@Service
public class ProdutosApiExternaService {
    @Value(value = "${header.api.externa.produto.userAgent}")
    public String userAgente;
    @Value(value = "${header.api.externa.produto.XCosmosToken}")
    public String xCosmosToken;
    public Triplet<RestTemplate,UriComponents,HttpEntity> resultQueryProdutoApiExterna(String query, String produto){
        RestTemplate template = new RestTemplate();

        UriComponents uri = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host("api.cosmos.bluesoft.com.br")
                .path(String.format("%s%s",query,produto))
                .build();


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Usuario-Agent",userAgente);
        headers.add("X-Cosmos-Token",xCosmosToken);
        HttpEntity entity = new HttpEntity(headers);

        return Triplet.with(template,uri,entity);
    }

    public byte[] resultQueryImageProdutoApiExterna(String gtin){

        RestTemplate templateImage = new RestTemplate();

        UriComponents uriImage = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("www.eanpictures.com.br:9000")
                .path(String.format("/api/gtin/%s",gtin))
                .build();

        byte[] imageBytes = templateImage.getForObject(uriImage.toUriString(), byte[].class);

        return imageBytes;
    }

    /*public List<Produto> listaProdutosApiExterna(ProdutosApiExterna produtos){
        List<Produto> produtoNew = Collections.singletonList(new Produto());

        produtos.products.forEach( produtoSalvar ->
                produtoNew.add(new Produto(
                        produtoSalvar.description,
                        produtoSalvar.brand != null ? produtoSalvar.brand.name : "",
                        produtoSalvar.gpc != null ? produtoSalvar.gpc.description: "",
                        produtoSalvar.cest != null ? produtoSalvar.cest.description :
                                produtoSalvar.ncm != null ? produtoSalvar.ncm.description : "",
                        produtoSalvar.gtins.size() < 0 ? "" :
                                produtoSalvar.gtins.get(0).commercial_unit != null ?
                                        produtoSalvar.gtins.get(0).commercial_unit.type_packaging : "",
                        produtoSalvar.gtins.size() < 0 ? "" :
                                produtoSalvar.gtins.get(0).commercial_unit != null ?
                                        produtoSalvar.gtins.get(0).commercial_unit.quantity_packaging : "",
                        produtoSalvar.gtin,
                        new BigDecimal(produtoSalvar.minPrice),
                        new BigDecimal(produtoSalvar.maxPrice),
                        new BigDecimal(produtoSalvar.avgPrice),
                        resultQueryImageProdutoApiExterna(produtoSalvar.gtin)
                ))
        );
        return produtoNew;
    }*/

    public Produto produto(ProdutoApiExterna produtoSalvar){
        Produto  produtoNew = new Produto(
                produtoSalvar.description,
                produtoSalvar.brand != null ? produtoSalvar.brand.name : "",
                produtoSalvar.gpc != null ? produtoSalvar.gpc.description: "",
                produtoSalvar.cest != null ? produtoSalvar.cest.description :
                        produtoSalvar.ncm != null ? produtoSalvar.ncm.description : "",
                produtoSalvar.gtins.size() < 0 ? "" :
                        produtoSalvar.gtins.get(0).commercial_unit != null ?
                                produtoSalvar.gtins.get(0).commercial_unit.type_packaging : "",
                produtoSalvar.gtins.size() < 0 ? "" :
                        produtoSalvar.gtins.get(0).commercial_unit != null ?
                                produtoSalvar.gtins.get(0).commercial_unit.quantity_packaging : "",
                produtoSalvar.gtin,
                new BigDecimal(produtoSalvar.minPrice),
                new BigDecimal(produtoSalvar.maxPrice),
                new BigDecimal(produtoSalvar.avgPrice),
                resultQueryImageProdutoApiExterna(produtoSalvar.gtin));

        return produtoNew;
    }
}
