package com.app.restApiAndroid.services.servicesvalidates.produto;

import com.app.restApiAndroid.models.dots.ValidationsDTO;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.repositories.ProdutoRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class ValidateInfoProdutoService {
    @Autowired
    private ValidateDatasProdutoService validateDatasProdutoService;
    public ValidationsDTO validInformations(ProdutoRepository produtoRepository, Produto data, int sessao, String id, String gtin){

        switch (sessao) {
            case 1: {
                Pair<Boolean,String> validaInfo = validateDatasProdutoService.validateDatasCreateProduto(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);
                if (produtoRepository.findAllByGtin(
                        validateDatasProdutoService.removerAcentosEspacoBranco(
                                data.getGtin().toUpperCase())).size() > 0)
                    return new ValidationsDTO(false, String.format("Produto com o código de barras %s já cadastrado!", data.getGtin().toUpperCase()), HttpStatus.BAD_REQUEST);
                if (produtoRepository.findAllByNome(
                        validateDatasProdutoService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() >0)
                    return new ValidationsDTO(false, String.format("Produto %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 2: {
                Pair<Boolean,String> validaInfo = validateDatasProdutoService.validateDatasUpdateProduto(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                Produto dadosprodutoCadastrado = produtoRepository.findById(id.toUpperCase()).orElse(null);

                if(dadosprodutoCadastrado == null)
                    return new ValidationsDTO(false, String.format("Produto com o id %s não encontrado!", id.toUpperCase()), HttpStatus.NOT_FOUND);

                String gtinCadastrado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        dadosprodutoCadastrado.getGtin().toUpperCase());
                String gtinInformado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        data.getGtin().toUpperCase());
                if (produtoRepository.findAllByGtin(
                        validateDatasProdutoService.removerAcentosEspacoBranco(
                                data.getGtin().toUpperCase())).size() > 0 &&
                !gtinInformado.equals(gtinCadastrado))
                    return new ValidationsDTO(false, String.format("Produto com o código de barras %s já cadastrado!", data.getGtin().toUpperCase()), HttpStatus.BAD_REQUEST);

                String nomeCadastrado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        dadosprodutoCadastrado.getNome().toUpperCase());
                String nomeInformado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        data.getNome().toUpperCase());
                if (produtoRepository.findAllByNome(
                        validateDatasProdutoService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() >0 &&
                !nomeInformado.equals(nomeCadastrado))
                    return new ValidationsDTO(false, String.format("Produto %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 3: {
                Pair<Boolean,String> validaInfo = validateDatasProdutoService.validateDatasUpdateProduto(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                Produto dadosprodutoCadastrado = produtoRepository.findByGtin(gtin.toUpperCase());

                if(dadosprodutoCadastrado == null)
                    return new ValidationsDTO(false, String.format("Produto com o código de barras %s não encontrado!", gtin.toUpperCase()), HttpStatus.BAD_REQUEST);

                String gtinCadastrado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        dadosprodutoCadastrado.getGtin().toUpperCase());
                String gtinInformado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        data.getGtin().toUpperCase());
                if (produtoRepository.findAllByGtin(
                        validateDatasProdutoService.removerAcentosEspacoBranco(
                                data.getGtin().toUpperCase())).size() > 0 &&
                        !gtinInformado.equals(gtinCadastrado))
                    return new ValidationsDTO(false, String.format("Produto com o código de barras %s já cadastrado!", data.getGtin().toUpperCase()), HttpStatus.BAD_REQUEST);

                String nomeCadastrado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        dadosprodutoCadastrado.getNome().toUpperCase());
                String nomeInformado = validateDatasProdutoService.removerAcentosEspacoBranco(
                        data.getNome().toUpperCase());
                if (produtoRepository.findAllByNome(
                        validateDatasProdutoService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() >0 &&
                        !nomeInformado.equals(nomeCadastrado))
                    return new ValidationsDTO(false, String.format("Produto %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
        }
        return new ValidationsDTO(true,"",null);
    }

}
