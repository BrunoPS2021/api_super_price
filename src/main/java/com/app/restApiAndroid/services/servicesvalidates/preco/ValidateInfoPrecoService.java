package com.app.restApiAndroid.services.servicesvalidates.preco;

import com.app.restApiAndroid.models.dots.PrecoDTO;
import com.app.restApiAndroid.models.dots.ValidationsDTO;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.models.preco.Preco;
import com.app.restApiAndroid.models.produto.Produto;
import com.app.restApiAndroid.models.usuario.DadosUsuario;
import com.app.restApiAndroid.repositories.PrecoRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ValidateInfoPrecoService {
    @Autowired
    private ValidateDatasPrecoService validateDatasPrecoService;
    public ValidationsDTO validInformations(PrecoRepository precoRepository,
                                            DadosUsuario usuario,
                                            Produto produto,
                                            Empresa empresa,
                                            PrecoDTO data,
                                            int sessao,
                                            String value,
                                            String value2,
                                            Produto produtoVindo,
                                            Empresa empresaVindo,
                                            String id){
        switch (sessao) {
            case 1: {
                ValidationsDTO validaInfo = CreateValidationsDTO(precoRepository, usuario, produto, empresa, data);
                if (validaInfo != null) return validaInfo;
            }
            break;
            case 2: {
                ValidationsDTO validaInfo = UpdateToIdValidationsDTO(precoRepository, usuario, produto, empresa, data,value);
                if (validaInfo != null) return validaInfo;
            }
            break;
            case 3: {
                ValidationsDTO validaInfo = UpdateToIdProdutoEmpresaValidationsDTO(precoRepository, usuario, produto, empresa, data,value,value2,produtoVindo,empresaVindo);
                if (validaInfo != null) return validaInfo;
            }
            break;
            case 4: {
                ValidationsDTO validaInfo = UpdateToIdPrecoProdutoEmpresaValidationsDTO(precoRepository, usuario, produto, empresa, data,value,value2,produtoVindo,empresaVindo,id);
                if (validaInfo != null) return validaInfo;
            }
            break;
        }
        return new ValidationsDTO(true,"",null);
    }

    private ValidationsDTO CreateValidationsDTO(PrecoRepository precoRepository,
                                                DadosUsuario usuario,
                                                Produto produto,
                                                Empresa empresa,
                                                PrecoDTO data) {
        Pair<Boolean,String> validaInfo = validateDatasPrecoService.validateDatasCreateProduto(data);
        if (!validaInfo.getValue0())
            return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);
        if(usuario == null)
            return new ValidationsDTO(false, String.format("Usuário com id %s não encontrado!", data.usuario().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(produto == null)
            return new ValidationsDTO(false, String.format("Produto com id %s não encontrado!", data.produto().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(empresa == null)
            return new ValidationsDTO(false, String.format("Empresa com id %s não encontrado!", data.empresa().getId().toUpperCase()), HttpStatus.BAD_REQUEST);

        List<Preco> precoProdutoExiste  = precoRepository.findAllByProdutoId(data.produto().getId());
        List<Preco> precoEmpresaExiste  = precoRepository.findAllByEmpresaId(data.empresa().getId());

        if(precoEmpresaExiste.size() > 0 && precoProdutoExiste.size() > 0) {
            List<String> idVindo= new ArrayList<>();
            List<String> idVindoAlterado= new ArrayList<>();

            for(Preco pro : precoProdutoExiste){
                for(Preco emp : precoEmpresaExiste){
                    if(pro.getId().equals(emp.getId()))
                        idVindo.add(pro.getId());
                }
            }


            for(String idExiste : idVindo){
                Preco precoOld = precoRepository.findById(idExiste.toUpperCase()).orElse(null);
                Preco precoNew = new Preco(new Date(),precoOld.getPrecoProdutoOfertado(),
                        usuario, produto, empresa,false);

                precoRepository.deleteById(precoOld.getId().toUpperCase());
                String idALterado = precoRepository.save(precoNew).getId().toUpperCase();
                idVindoAlterado.add(idALterado);
            }

            if(idVindoAlterado.size() >= 5)
            {
                for(String idExiste : idVindoAlterado)
                {
                    precoRepository.deleteById(idExiste.toUpperCase());
                }
            }
        }
        return null;
    }
    private ValidationsDTO UpdateToIdValidationsDTO(PrecoRepository precoRepository,
                                                    DadosUsuario usuario,
                                                    Produto produto,
                                                    Empresa empresa,
                                                    PrecoDTO data,
                                                    String value) {
        Pair<Boolean,String> validaInfo = validateDatasPrecoService.validateDatasUpdateProduto(data);
        if (!validaInfo.getValue0())
            return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);
        if(usuario == null)
            return new ValidationsDTO(false, String.format("Usuário com id %s não encontrado!", data.usuario().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(produto == null)
            return new ValidationsDTO(false, String.format("Produto com id %s não encontrado!", data.produto().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(empresa == null)
            return new ValidationsDTO(false, String.format("Empresa com id %s não encontrado!", data.empresa().getId().toUpperCase()), HttpStatus.BAD_REQUEST);

        Preco precoVindo = precoRepository.findById(value).orElse(null);
        if(precoVindo == null)
            return new ValidationsDTO(false, String.format("Preço com id %s não encontrado!", value.toUpperCase()), HttpStatus.BAD_REQUEST);

        List<Preco> precoProdutoExiste  = precoRepository.findAllByProdutoId(data.produto().getId());
        List<Preco> precoEmpresaExiste  = precoRepository.findAllByEmpresaId(data.empresa().getId());

        if(precoEmpresaExiste.size() > 0 && precoProdutoExiste.size() > 0) {
            List<String> idVindo= new ArrayList<>();
            List<String> idVindoAlterado= new ArrayList<>();

            for(Preco pro : precoProdutoExiste){
                for(Preco emp : precoEmpresaExiste){
                    if(pro.getId().equals(emp.getId()))
                        idVindo.add(pro.getId());
                }
            }


            for(String idExiste : idVindo){
                Preco precoOld = precoRepository.findById(idExiste.toUpperCase()).orElse(null);
                Preco precoNew = new Preco(new Date(),precoOld.getPrecoProdutoOfertado(),
                        usuario, produto, empresa,false);

                precoRepository.deleteById(precoOld.getId().toUpperCase());
                String idALterado = precoRepository.save(precoNew).getId().toUpperCase();
                idVindoAlterado.add(idALterado);
            }

            if(idVindoAlterado.size() >= 5)
            {
                for(String idExiste : idVindoAlterado)
                {
                    precoRepository.deleteById(idExiste.toUpperCase());
                }
            }
        }
        return null;
    }
    private ValidationsDTO UpdateToIdProdutoEmpresaValidationsDTO(PrecoRepository precoRepository,
                                                           DadosUsuario usuario,
                                                           Produto produto,
                                                           Empresa empresa,
                                                           PrecoDTO data,
                                                           String value,
                                                           String value2,
                                                           Produto produtoVindo,
                                                           Empresa empresaVindo) {
        Pair<Boolean,String> validaInfo = validateDatasPrecoService.validateDatasUpdateProduto(data);
        if (!validaInfo.getValue0())
            return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);
        if(usuario == null)
            return new ValidationsDTO(false, String.format("Usuário com id %s não encontrado!", data.usuario().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(produto == null)
            return new ValidationsDTO(false, String.format("Produto com id %s não encontrado!", data.produto().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(empresa == null)
            return new ValidationsDTO(false, String.format("Empresa com id %s não encontrado!", data.empresa().getId().toUpperCase()), HttpStatus.BAD_REQUEST);

        if(produtoVindo == null)
            return new ValidationsDTO(false, String.format("Produto informado com id %s não encontrado!", data.produto().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(empresaVindo == null)
            return new ValidationsDTO(false, String.format("Empresa informada com id %s não encontrado!", data.empresa().getId().toUpperCase()), HttpStatus.BAD_REQUEST);

        List<Preco> precoProdutoVindo  = precoRepository.findAllByProdutoId(value.toUpperCase());
        List<Preco> precoEmpresaVindo  = precoRepository.findAllByEmpresaId(value2.toUpperCase());

        if(precoEmpresaVindo.isEmpty() && precoProdutoVindo.isEmpty()){
            return new ValidationsDTO(false,
                    String.format("Preço do produto %s na empresa %s não encontrado!",
                            produtoVindo.getNome().toUpperCase(),
                            empresaVindo.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
        }
        else if(precoEmpresaVindo.isEmpty()){
            return new ValidationsDTO(false,
                    String.format("Preço do produto %s na empresa %s não encontrado!",
                            produtoVindo.getNome().toUpperCase(),
                            empresaVindo.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
        }
        else if(precoProdutoVindo.isEmpty()){
            return new ValidationsDTO(false,
                    String.format("Preço do produto %s na empresa %s não encontrado!",
                            produtoVindo.getNome().toUpperCase(),
                            empresaVindo.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
        }

        List<Preco> precoProdutoExiste  = precoRepository.findAllByProdutoId(data.produto().getId());
        List<Preco> precoEmpresaExiste  = precoRepository.findAllByEmpresaId(data.empresa().getId());

        if(precoProdutoExiste.isEmpty())
            return new ValidationsDTO(false, String.format("Preço do produto %s da empresa %s não encontrado!",
                    value.toUpperCase(),value2.toUpperCase()), HttpStatus.BAD_REQUEST);

        if(precoEmpresaExiste.size() > 0 && precoProdutoExiste.size() > 0) {
            List<String> idVindo= new ArrayList<>();
            List<String> idVindoAlterado= new ArrayList<>();

            for(Preco pro : precoProdutoExiste){
                for(Preco emp : precoEmpresaExiste){
                    if(pro.getId().equals(emp.getId()))
                        idVindo.add(pro.getId());
                }
            }


            for(String idExiste : idVindo){
                Preco precoOld = precoRepository.findById(idExiste.toUpperCase()).orElse(null);
                Preco precoNew = new Preco(new Date(),precoOld.getPrecoProdutoOfertado(),
                        usuario, produto, empresa,false);

                precoRepository.deleteById(precoOld.getId().toUpperCase());
                String idALterado = precoRepository.save(precoNew).getId().toUpperCase();
                idVindoAlterado.add(idALterado);
            }

            if(idVindoAlterado.size() >= 5)
            {
                for(String idExiste : idVindoAlterado)
                {
                    precoRepository.deleteById(idExiste.toUpperCase());
                }
            }
        }
        return null;
    }
    private ValidationsDTO UpdateToIdPrecoProdutoEmpresaValidationsDTO(PrecoRepository precoRepository,
                                                                  DadosUsuario usuario,
                                                                  Produto produto,
                                                                  Empresa empresa,
                                                                  PrecoDTO data,
                                                                  String value,
                                                                  String value2,
                                                                  Produto produtoVindo,
                                                                  Empresa empresaVindo,
                                                                  String id) {
        Pair<Boolean,String> validaInfo = validateDatasPrecoService.validateDatasUpdateProduto(data);
        if (!validaInfo.getValue0())
            return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

        Preco precoVindo = precoRepository.findById(id).orElse(null);
        if(precoVindo == null)
            return new ValidationsDTO(false, String.format("Preço com id %s não encontrado!", id.toUpperCase()), HttpStatus.BAD_REQUEST);


        if(usuario == null)
            return new ValidationsDTO(false, String.format("Usuário com id %s não encontrado!", data.usuario().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(produto == null)
            return new ValidationsDTO(false, String.format("Produto com id %s não encontrado!", data.produto().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(empresa == null)
            return new ValidationsDTO(false, String.format("Empresa com id %s não encontrado!", data.empresa().getId().toUpperCase()), HttpStatus.BAD_REQUEST);

        if(produtoVindo == null)
            return new ValidationsDTO(false, String.format("Produto informado com id %s não encontrado!", data.produto().getId().toUpperCase()), HttpStatus.BAD_REQUEST);
        if(empresaVindo == null)
            return new ValidationsDTO(false, String.format("Empresa informada com id %s não encontrado!", data.empresa().getId().toUpperCase()), HttpStatus.BAD_REQUEST);

        List<Preco> precoProdutoVindo  = precoRepository.findAllByProdutoId(value.toUpperCase());
        List<Preco> precoEmpresaVindo  = precoRepository.findAllByEmpresaId(value2.toUpperCase());

        if(precoEmpresaVindo.isEmpty() && precoProdutoVindo.isEmpty()){
            return new ValidationsDTO(false,
                    String.format("Preço do produto %s na empresa %s não encontrado!",
                            produtoVindo.getNome().toUpperCase(),
                            empresaVindo.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
        }
        else if(precoEmpresaVindo.isEmpty()){
            return new ValidationsDTO(false,
                    String.format("Preço do produto %s na empresa %s não encontrado!",
                            produtoVindo.getNome().toUpperCase(),
                            empresaVindo.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
        }
        else if(precoProdutoVindo.isEmpty()){
            return new ValidationsDTO(false,
                    String.format("Preço do produto %s na empresa %s não encontrado!",
                            produtoVindo.getNome().toUpperCase(),
                            empresaVindo.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
        }

        List<Preco> precoProdutoExiste  = precoRepository.findAllByProdutoId(data.produto().getId());
        List<Preco> precoEmpresaExiste  = precoRepository.findAllByEmpresaId(data.empresa().getId());

        if(precoProdutoExiste.isEmpty())
            return new ValidationsDTO(false, String.format("Preço do produto %s da empresa %s não encontrado!",
                    value.toUpperCase(),value2.toUpperCase()), HttpStatus.BAD_REQUEST);

        if(precoEmpresaExiste.size() > 0 && precoProdutoExiste.size() > 0) {
            List<String> idVindo= new ArrayList<>();
            List<String> idVindoAlterado= new ArrayList<>();

            for(Preco pro : precoProdutoExiste){
                for(Preco emp : precoEmpresaExiste){
                    if(pro.getId().equals(emp.getId()))
                        idVindo.add(pro.getId());
                }
            }


            for(String idExiste : idVindo){
                Preco precoOld = precoRepository.findById(idExiste.toUpperCase()).orElse(null);
                Preco precoNew = new Preco(new Date(),precoOld.getPrecoProdutoOfertado(),
                        usuario, produto, empresa,false);

                precoRepository.deleteById(precoOld.getId().toUpperCase());
                String idALterado = precoRepository.save(precoNew).getId().toUpperCase();
                idVindoAlterado.add(idALterado);
            }

            if(idVindoAlterado.size() >= 5)
            {
                for(String idExiste : idVindoAlterado)
                {
                    precoRepository.deleteById(idExiste.toUpperCase());
                }
            }
        }
        return null;
    }
}
