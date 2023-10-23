package com.app.restApiAndroid.services.servicesvalidates.usuario;

import com.app.restApiAndroid.models.usuario.DadosUsuario;
import com.app.restApiAndroid.models.dots.AuthenticationDTO;
import com.app.restApiAndroid.models.dots.RegisterDTO;
import com.app.restApiAndroid.models.dots.ValidationsDTO;
import com.app.restApiAndroid.repositories.DadosUsuarioRepository;
import com.app.restApiAndroid.repositories.UsuarioRepository;
import org.javatuples.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@Service
public class ValidateInfoUsuarioService {
    @Autowired()
    private ValidateDatasUsuarioService validateDatasUsuarioService;

    public ValidationsDTO validInformations(DadosUsuarioRepository dadosUsuarioRepository, UsuarioRepository usuarioRepository, RegisterDTO dataRegister, AuthenticationDTO dataAuth, int sessao, String id, String login) throws UnsupportedEncodingException, NoSuchAlgorithmException {


        switch (sessao) {
            case 1: {
                Pair<Boolean,String> validaInfo = validateDatasUsuarioService.validateDatasCreateUser(dataRegister);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);
                if (dadosUsuarioRepository.findAllByEmail(
                        validateDatasUsuarioService.removerAcentosEspacoBranco(
                                dataRegister.dadosUsuario().getEmail().toUpperCase())).size() > 0)
                    return new ValidationsDTO(false, String.format("Email %s já cadastrado!", dataRegister.dadosUsuario().getEmail()), HttpStatus.BAD_REQUEST);
                if (usuarioRepository.findByLogin(
                        validateDatasUsuarioService.removerAcentosEspacoBranco(
                                dataRegister.login().toUpperCase())) != null)
                    return new ValidationsDTO(false, String.format("Usuário %s já cadastrado!", dataRegister.login()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 2: {
                Pair<Boolean,String> validaNull = validateDatasUsuarioService.validateDatasLoginNull(dataAuth);
                var user = usuarioRepository.findByLogin(dataAuth.login());
                if (!validaNull.getValue0())
                    return new ValidationsDTO(false, validaNull.getValue1(), HttpStatus.NOT_ACCEPTABLE);
                if (user == null)
                    return new ValidationsDTO(false,String.format("Usuário %s não encontrado!", dataAuth.login()), HttpStatus.NOT_FOUND);
                else if (!new BCryptPasswordEncoder().matches(dataAuth.password(), user.getPassword()))
                    return new ValidationsDTO(false,String.format("Senha Incorreta!"), HttpStatus.BAD_REQUEST);
            }
            break;
            case 3: {
                Pair<Boolean,String> validaInfo = validateDatasUsuarioService.validateDatasUpdateUser(dataRegister);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);

                if (dadosUsuarioRepository.findByUserId(id.toUpperCase()) == null)
                    return new ValidationsDTO(false, String.format("Usuário com id %s não encontrado cadastrado!", id), HttpStatus.NOT_FOUND);


                DadosUsuario dadosUsuarioCadastrados = dadosUsuarioRepository.findByUserId(id.toUpperCase());

                String emailCadastado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dadosUsuarioCadastrados.getEmail().toUpperCase());
                String emailInformado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dataRegister.dadosUsuario().getEmail());
                if (dadosUsuarioRepository.findAllByEmail(
                        validateDatasUsuarioService.removerAcentosEspacoBranco(
                                dataRegister.dadosUsuario()
                        .getEmail().toUpperCase())).size() > 0 &&
                !emailCadastado.equals(emailInformado))
                    return new ValidationsDTO(false, String.format("Email %s já cadastrado!", dataRegister.dadosUsuario().getEmail()), HttpStatus.BAD_REQUEST);

                String loginCadastrado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dadosUsuarioCadastrados.getUser().getUsername().toUpperCase());
                String loginInformado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dataRegister.login().toUpperCase());
                if (usuarioRepository.findByLogin(
                        validateDatasUsuarioService.removerAcentosEspacoBranco(
                                dataRegister.login().toUpperCase())) != null &&
                !loginCadastrado.equals(loginInformado))
                    return new ValidationsDTO(false, String.format("Usuário %s já cadastrado!", dataRegister.login()), HttpStatus.BAD_REQUEST);
            }
            break;
            case 4: {
                Pair<Boolean,String> validaInfo = validateDatasUsuarioService.validateDatasUpdateUser(dataRegister);
                if (!validaInfo.getValue0())
                    return new ValidationsDTO(false, validaInfo.getValue1(), HttpStatus.NOT_ACCEPTABLE);


                if (dadosUsuarioRepository.findByUserLogin(login.toUpperCase()) == null)
                    return new ValidationsDTO(false, String.format("Usuário %s não encontrado cadastrado!", login), HttpStatus.NOT_FOUND);

                DadosUsuario dadosUsuarioCadastrados = dadosUsuarioRepository.findByUserLogin(login.toUpperCase());

                String emailCadastado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dadosUsuarioCadastrados.getEmail().toUpperCase());
                String emailInformado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dataRegister.dadosUsuario().getEmail());
                if (dadosUsuarioRepository.findAllByEmail(
                        validateDatasUsuarioService.removerAcentosEspacoBranco(
                                dataRegister.dadosUsuario().getEmail().toUpperCase()))
                        .size() > 0 &&
                !emailCadastado.equals(emailInformado))
                    return new ValidationsDTO(false, String.format("Email %s já cadastrado!", dataRegister.dadosUsuario().getEmail()), HttpStatus.BAD_REQUEST);

                String loginCadastrado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dadosUsuarioCadastrados.getUser().getUsername().toUpperCase());
                String loginInformado = validateDatasUsuarioService.removerAcentosEspacoBranco(
                        dataRegister.login().toUpperCase());
                if (usuarioRepository.findByLogin(
                        validateDatasUsuarioService.removerAcentosEspacoBranco(
                                dataRegister.login().toUpperCase())) != null &&
                    !loginCadastrado.equals(loginInformado))
                    return new ValidationsDTO(false, String.format("Usuário %s já cadastrado!", dataRegister.login()), HttpStatus.BAD_REQUEST);
            }
            break;

        }
        return new ValidationsDTO(true,"",null);
    }

}
