package com.app.restApiAndroid.services.servicesvalidates.empresa;

import com.app.restApiAndroid.models.dots.ValidationsDTO;
import com.app.restApiAndroid.models.empresa.Empresa;
import com.app.restApiAndroid.repositories.EmpresaRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class ValidateInfoEmpresaService {
    @Autowired
    private ValidateDatasEmpresaService validateDatasEmpresaService;
    public ValidationsDTO validInformations(EmpresaRepository empresaRepository, Empresa data, int sessao, String value) {

        switch (sessao) {
            case 1: {
                Pair<Boolean,String> validaInfo = validateDatasEmpresaService.validateDatasCreateEmpresa(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);
                if (empresaRepository.findAllByCnpj(data.getCnpj().toUpperCase()).size() > 0 &&
                !data.getCnpj().toUpperCase().equals("NAOINFORMADO".toUpperCase()))
                    return new ValidationsDTO(false, String.format("Empresa com o CNPJ %s já cadastrado!", data.getCnpj().toUpperCase()), HttpStatus.BAD_REQUEST);
                if (empresaRepository.findAllByNome(data.getNome().toUpperCase()).size() >0)
                    return new ValidationsDTO(false, String.format("Empresa com nome %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);
                if (empresaRepository.findAllByFantasia(data.getFantasia().toUpperCase()).size() >0)
                    return new ValidationsDTO(false, String.format("Empresa com nome fantasia %s já cadastrado!", data.getFantasia().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 2: {
                Pair<Boolean, String> validaInfo = validateDatasEmpresaService.validateDatasUpdateEmpresa(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                if (!empresaRepository.existsById(value.toUpperCase()))
                    return new ValidationsDTO(false, String.format("Empresa com id %s não encontrado cadastrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                Empresa dadosEmpresaCadastrado = empresaRepository.findById(value.toUpperCase()).orElseGet(null);

                if (dadosEmpresaCadastrado == null)
                    return new ValidationsDTO(false, String.format("Empresa com o id %s não encontrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                String cnpjCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getCnpj().toUpperCase());
                String cnpjInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getCnpj().toUpperCase());
                if (empresaRepository.findAllByCnpj(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getCnpj().toUpperCase())).size() > 0 &&
                        !cnpjCadastrado.equals(cnpjInformado) &&
                        !cnpjCadastrado.equals("NAOINFORMADO".toUpperCase()) &&
                        !cnpjInformado.equals("NAOINFORMADO".toUpperCase())
                )
                    return new ValidationsDTO(false, String.format("Empresa com o cnpj %s já cadastrado!", data.getCnpj().toUpperCase()), HttpStatus.BAD_REQUEST);

                String nomeCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getNome().toUpperCase());
                String nomeInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getNome().toUpperCase());
                if (empresaRepository.findAllByNomeContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() > 0 &&
                        !nomeCadastrado.equals(nomeInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);

                String fantasiaCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getFantasia().toUpperCase());
                String fantasiaInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getFantasia().toUpperCase());
                if (empresaRepository.findAllByFantasiaContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getFantasia().toUpperCase())).size() > 0 &&
                        !fantasiaCadastrado.equals(fantasiaInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome fantasia %s já cadastrado!", data.getFantasia().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 3: {
                Pair<Boolean, String> validaInfo = validateDatasEmpresaService.validateDatasUpdateEmpresa(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                if (!empresaRepository.existsByCnpj(value.toUpperCase()))
                    return new ValidationsDTO(false, String.format("Empresa com cnpj %s não encontrado cadastrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                Empresa dadosEmpresaCadastrado = empresaRepository.findByCnpj(value.toUpperCase());

                if (dadosEmpresaCadastrado == null)
                    return new ValidationsDTO(false, String.format("Empresa com o cnpj %s não encontrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                String cnpjCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getCnpj().toUpperCase());
                String cnpjInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getCnpj().toUpperCase());
                if (empresaRepository.findAllByCnpj(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getCnpj().toUpperCase())).size() > 0 &&
                        !cnpjCadastrado.equals(cnpjInformado) &&
                        !cnpjCadastrado.equals("NAOINFORMADO".toUpperCase()) &&
                        !cnpjInformado.equals("NAOINFORMADO".toUpperCase())
                )
                    return new ValidationsDTO(false, String.format("Empresa com o cnpj %s já cadastrado!", data.getCnpj().toUpperCase()), HttpStatus.BAD_REQUEST);

                String nomeCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getNome().toUpperCase());
                String nomeInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getNome().toUpperCase());
                if (empresaRepository.findAllByNomeContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() > 0 &&
                        !nomeCadastrado.equals(nomeInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);

                String fantasiaCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getFantasia().toUpperCase());
                String fantasiaInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getFantasia().toUpperCase());
                if (empresaRepository.findAllByFantasiaContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getFantasia().toUpperCase())).size() > 0 &&
                        !fantasiaCadastrado.equals(fantasiaInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome fantasia %s já cadastrado!", data.getFantasia().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 4: {
                Pair<Boolean, String> validaInfo = validateDatasEmpresaService.validateDatasUpdateEmpresa(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                if (!empresaRepository.existsByNome(value.toUpperCase()))
                    return new ValidationsDTO(false, String.format("Empresa com nome %s não encontrado cadastrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                Empresa dadosEmpresaCadastrado = empresaRepository.findByNome(value.toUpperCase());

                if (dadosEmpresaCadastrado == null)
                    return new ValidationsDTO(false, String.format("Empresa com o nome %s não encontrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                String cnpjCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getCnpj().toUpperCase());
                String cnpjInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getCnpj().toUpperCase());
                if (empresaRepository.findAllByCnpj(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getCnpj().toUpperCase())).size() > 0 &&
                        !cnpjCadastrado.equals(cnpjInformado) &&
                        !cnpjCadastrado.equals("NAOINFORMADO".toUpperCase()) &&
                        !cnpjInformado.equals("NAOINFORMADO".toUpperCase())
                )
                    return new ValidationsDTO(false, String.format("Empresa com o cnpj %s já cadastrado!", data.getCnpj().toUpperCase()), HttpStatus.BAD_REQUEST);

                String nomeCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getNome().toUpperCase());
                String nomeInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getNome().toUpperCase());
                if (empresaRepository.findAllByNomeContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() > 0 &&
                        !nomeCadastrado.equals(nomeInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);

                String fantasiaCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getFantasia().toUpperCase());
                String fantasiaInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getFantasia().toUpperCase());
                if (empresaRepository.findAllByFantasiaContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getFantasia().toUpperCase())).size() > 0 &&
                        !fantasiaCadastrado.equals(fantasiaInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome fantasia %s já cadastrado!", data.getFantasia().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 5: {
                Pair<Boolean, String> validaInfo = validateDatasEmpresaService.validateDatasUpdateEmpresa(data);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                if (!empresaRepository.existsByFantasia(value.toUpperCase()))
                    return new ValidationsDTO(false, String.format("Empresa com nome fantasia %s não encontrado cadastrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                Empresa dadosEmpresaCadastrado = empresaRepository.findByFantasia(value.toUpperCase());

                if (dadosEmpresaCadastrado == null)
                    return new ValidationsDTO(false, String.format("Empresa com o nome fantasia %s não encontrado!", value.toUpperCase()), HttpStatus.NOT_FOUND);

                String cnpjCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getCnpj().toUpperCase());
                String cnpjInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getCnpj().toUpperCase());
                if (empresaRepository.findAllByCnpj(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getCnpj().toUpperCase())).size() > 0 &&
                        !cnpjCadastrado.equals(cnpjInformado) &&
                        !cnpjCadastrado.equals("NAOINFORMADO".toUpperCase()) &&
                        !cnpjInformado.equals("NAOINFORMADO".toUpperCase())
                )
                    return new ValidationsDTO(false, String.format("Empresa com o cnpj %s já cadastrado!", data.getCnpj().toUpperCase()), HttpStatus.BAD_REQUEST);

                String nomeCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getNome().toUpperCase());
                String nomeInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getNome().toUpperCase());
                if (empresaRepository.findAllByNomeContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getNome().toUpperCase())).size() > 0 &&
                        !nomeCadastrado.equals(nomeInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome %s já cadastrado!", data.getNome().toUpperCase()), HttpStatus.BAD_REQUEST);

                String fantasiaCadastrado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        dadosEmpresaCadastrado.getFantasia().toUpperCase());
                String fantasiaInformado = validateDatasEmpresaService.removerAcentosEspacoBranco(
                        data.getFantasia().toUpperCase());
                if (empresaRepository.findAllByFantasiaContaining(
                        validateDatasEmpresaService.removerAcentosEspacoBranco(
                                data.getFantasia().toUpperCase())).size() > 0 &&
                        !fantasiaCadastrado.equals(fantasiaInformado))
                    return new ValidationsDTO(false, String.format("Empresa com o nome fantasia %s já cadastrado!", data.getFantasia().toUpperCase()), HttpStatus.BAD_REQUEST);
            }
            break;
        }

        return new ValidationsDTO(true,"",null);
    }
}
