package com.app.restApiAndroid.services.servicesvalidates.usuario;

import com.app.restApiAndroid.enums.UserRole;
import com.app.restApiAndroid.models.usuario.Endereco;
import com.app.restApiAndroid.models.usuario.Contatos;
import com.app.restApiAndroid.models.dots.AuthenticationDTO;
import com.app.restApiAndroid.models.dots.RegisterDTO;
import org.apache.commons.lang3.StringUtils;
import org.javatuples.Pair;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.regex.Pattern;

import java.io.*;
import java.util.*;

@Service
public class ValidateDatasUsuarioService {

    public Pair<Boolean, String> validateDatasUpdateUser(RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Pair<Boolean, String> validateDataAcesso = validateDatasAcesso(data);
        if (!validateDataAcesso.getValue0())
            return validateDataAcesso;

        Pair<Boolean, String> validateDataUser = validateDatasUser(data);
        if (!validateDataUser.getValue0())
            return validateDataUser;

        Pair<Boolean, String> validateEndereco = validateDatasEndereco(data);
        if (!validateEndereco.getValue0())
            return validateEndereco;

        Pair<Boolean, String> validateContato = validateDatasContatos(data);
        if (!validateContato.getValue0())
            return validateContato;


        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasCreateUser(RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {

        Pair<Boolean, String> validateDataAcesso = validateDatasAcesso(data);
        if (!validateDataAcesso.getValue0())
            return validateDataAcesso;

        Pair<Boolean, String> validateDataUser = validateDatasUser(data);
        if (!validateDataUser.getValue0())
            return validateDataUser;

        Pair<Boolean, String> validateEndereco = validateDatasEndereco(data);
        if (!validateEndereco.getValue0())
            return validateEndereco;

        Pair<Boolean, String> validateContato = validateDatasContatos(data);
        if (!validateContato.getValue0())
            return validateContato;


        upperCaseData(data);

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasUser(RegisterDTO data) {

        if (data.dadosUsuario().getName().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo nome não pode esta vazio!"));
        if (data.dadosUsuario().getEmail().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo email não pode esta vazio!"));
        if (!isValidEmailAddress(data.dadosUsuario().getEmail()))
            return Pair.with(false, String.format("Campo email com formato invalido (%s)!",
                    data.dadosUsuario().getEmail()));
        if(data.dadosUsuario().getNascimento().toString().isEmpty())
            return Pair.with(false, String.format("Campo nascimento não pode esta vazio!"));
        if(!isValid(data.dadosUsuario().getNascimento().toString()))
            return Pair.with(false, String.format("Formato do nascimento invalido dd/MM/yyyy!"));

        return Pair.with(true, "");
    }
    public boolean isValid(String strDate) {
        String dateFormat = "dd/MM/uuuu";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(strDate, dateTimeFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
    public Pair<Boolean, String> validateDatasAcesso(RegisterDTO data) {
        if (data.login().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo login não pode esta vazio!"));
        if (data.password().replace(" ", "").isEmpty())
            return Pair.with(false, String.format("Campo senha não pode esta vazio!"));
        if (data.role().equals(UserRole.INVALIDO))
            return Pair.with(false, String.format("Role não existe, as opções são: %s!",
                    UserRole.userRolesText()));
        if (data.role().equals(UserRole.NULLFIELD))
            return Pair.with(false, String.format("Campo role não pode esta vazio, as opções " +
                    "são: %s!", UserRole.userRolesText()));

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasEndereco(RegisterDTO data) {
        if (data.dadosUsuario().getEnderecos() == null ||
        data.dadosUsuario().getEnderecos().isEmpty())
            return Pair.with(false, String.format("Informe pelo menos um endereço!"));

        for (Endereco endereco : data.dadosUsuario().getEnderecos()) {
            if (endereco.getTipoLogradouro().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo tipo logradouro não pode esta vazio!"));
            if (endereco.getLogradouro().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo logradouro não pode esta vazio!"));
            if (endereco.getCidade().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo cidade não pode esta vazio!"));
            if (endereco.getBairro().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo bairro não pode esta vazio!"));
        }

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
    public Pair<Boolean, String> validateDatasFormatoNumeroEndereco(RegisterDTO data) {

        if (data.dadosUsuario().getEnderecos() == null ||
                data.dadosUsuario().getEnderecos().isEmpty())
            return Pair.with(false, String.format("Informe pelo menos um endereço!"));

        for (Endereco endereco : data.dadosUsuario().getEnderecos()) {
            if (endereco.getNumero().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo número não pode esta vazio!"));
            if (!StringUtils.isNumeric(endereco.getNumero()) &&
                    !endereco.getNumero().toUpperCase().equals("S/N") &&
                    !endereco.getNumero().toUpperCase().equals("SN"))
                return Pair.with(false, String.format("Campo número só aceita números, s/n ou sn (%s)!",
                        endereco.getNumero()));

        }
        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasFormatoUF(RegisterDTO data) {

        if (data.dadosUsuario().getEnderecos() == null ||
                data.dadosUsuario().getEnderecos().isEmpty())
            return Pair.with(false, String.format("Informe pelo menos um endereço!"));

        for (Endereco endereco : data.dadosUsuario().getEnderecos()) {

            if (endereco.getUf().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo UF não pode esta vazio!"));
            if (endereco.getUf().length() != 2)
                return Pair.with(false, String.format("Campo UF com tamanho %d, o esperado é 2!",
                        endereco.getUf().length()));
            for (char caracter : endereco.getUf().toCharArray()) {
                if (StringUtils.isNumeric(Character.toString(caracter)))
                    return Pair.with(false, String.format("Campo UF só aceita carateres (%s)!", endereco.getUf()));

            }
        }

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasFormatoCep(RegisterDTO data) {
        if (data.dadosUsuario().getEnderecos() == null ||
                data.dadosUsuario().getEnderecos().isEmpty())
            return Pair.with(false, String.format("Informe pelo menos um endereço!"));

        for (Endereco endereco : data.dadosUsuario().getEnderecos()) {
            if (endereco.getCep().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo CEP não pode esta vazio!"));
            if (!StringUtils.isNumeric(endereco.getCep()))
                return Pair.with(false, String.format("Campo CEP só aceita números (%s)!", endereco.getCep()));
            if (endereco.getCep().length() != 8)
                return Pair.with(false, String.format("Campo CEP deve conter 8 números (%d)!",
                        endereco.getCep().length()));
            if(endereco.getCep().charAt(0) == '0')
                return Pair.with(false, String.format("Campo CEP não pode iniciar com zero (%d)!",
                        endereco.getCep()));
        }

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasContatos(RegisterDTO data) {
        if (data.dadosUsuario().getContatos() == null ||
                data.dadosUsuario().getEnderecos().isEmpty())
            return Pair.with(false,String.format("Informe pelo menos um contato!"));

        for(Contatos contato : data.dadosUsuario().getContatos()){
            if (contato.getDdd().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo ddd não pode ser vazio!"));
            if (!StringUtils.isNumeric(contato.getDdd()))
                return Pair.with(false, String.format("Campo ddd deve conter apenas números!"));
            if (contato.getDdd().length() != 2)
                return Pair.with(false, String.format("Campo ddd com tamanho %d, o esperado é 2!",
                        contato.getDdd().length()));
            if(contato.getDdd().charAt(0) == '0')
                return Pair.with(false, String.format("Campo ddd não pode iniciar com zero (%s)!",
                        contato.getDdd()));

            if (contato.getTelefone().replace(" ", "").isEmpty())
                return Pair.with(false, String.format("Campo telefone não pode ser vazio!"));
            if (!StringUtils.isNumeric(contato.getTelefone()))
                return Pair.with(false, String.format("Campo telefone deve conter apenas números!"));

            Pattern celular = Pattern.compile(
                    "[9]{1}[1-9]{4}[0-9]{4}");
            Pattern comercial = Pattern.compile(
                    "[1-9]{1}[0-9]{3}[0-9]{4}");

            if (!comercial.matcher(contato.getTelefone()).matches() && contato.getTelefone().length() == 8)
                return Pair.with(false, String.format("Campo telefone (%s) do tipo comercial deve " +
                        "conter 8 números (%d) [33333333]!", contato.getTelefone(), contato.getTelefone().length()));
            else if (!celular.matcher(contato.getTelefone()).matches() && contato.getTelefone().length() == 9)
                return Pair.with(false, String.format("Campo telefone (%s) do tipo celular deve " +
                        "conter 9 números (%d) [999999999]!", contato.getTelefone(), contato.getTelefone().length()));
            else if (!(contato.getTelefone().length() >= 8 && contato.getTelefone().length() <= 9))
                return Pair.with(false, String.format("Campo telefone (%s) do tipo celular deve conter " +
                        "9 números [933333333] ou do tipo comercial deve conter 8 números (%d) " +
                        "[33333333]!", contato.getTelefone(), contato.getTelefone().length()));

        }

        return Pair.with(true, "");
    }
    public Pair<Boolean, String> validateDatasLoginNull(AuthenticationDTO data) {
        if (data.login().replace(" ","").isEmpty())
            return Pair.with(false, String.format("Campo login não pode esta vazio!"));
        if (data.password().replace(" ","").isEmpty())
            return Pair.with(false, String.format("Campo senha não pode esta vazio!"));

        return Pair.with(true, "");
    }
    private static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    private void upperCaseData(RegisterDTO data) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        data.dadosUsuario().setName(removerAcentosEspacoBranco(data.dadosUsuario().getName().toUpperCase()));
        data.dadosUsuario().setEmail(removerAcentosEspacoBranco(data.dadosUsuario().getEmail().toUpperCase()));

        data.dadosUsuario().setEnderecos(obterAddressRepetidos(data.dadosUsuario().getEnderecos()));
        data.dadosUsuario().setContatos(obterContatosRepetidos(data.dadosUsuario().getContatos()));

        for(Endereco end : data.dadosUsuario().getEnderecos()){
            end.setTipoLogradouro(removerAcentosEspacoBranco(end.getTipoLogradouro().toUpperCase()));
            end.setLogradouro(removerAcentosEspacoBranco(end.getLogradouro().toUpperCase()));
            end.setUf(removerAcentosEspacoBranco(end.getUf().toUpperCase()));
            end.setCidade(removerAcentosEspacoBranco(end.getCidade().toUpperCase()));
            end.setComplemento(removerAcentosEspacoBranco(end.getComplemento().toUpperCase()));
            end.setPontoReferencia(removerAcentosEspacoBranco(end.getPontoReferencia().toUpperCase()));
            end.setBairro(removerAcentosEspacoBranco(end.getBairro().toUpperCase()));
        }
    }
    public List<Contatos> obterContatosRepetidos(List<Contatos> lista) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        List<Pair<Contatos,String>> pairEnd = new ArrayList<>();


        for (Contatos contato : lista){
            StringBuilder contatoLista = new StringBuilder("; ");

            contatoLista.append(removerAcentosEspacoBranco(contato.getDdd()));
            contatoLista.append(removerAcentosEspacoBranco(contato.getTelefone()));


            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(contatoLista.toString().getBytes("UTF-8"));


            StringBuilder hash = new StringBuilder();
            for (byte b : messageDigest) {
                hash.append(String.format("%02X", 0xFF & b));
            }

            pairEnd.add(Pair.with(contato,hash.toString()));
        }

        return pairEnd.stream().distinct().map(x->x.getValue0()).toList();
    }
    public List<Endereco> obterAddressRepetidos(List<Endereco> lista) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        List<Pair<Endereco,String>> pairEnd = new ArrayList<>();


        for (Endereco end : lista){
            StringBuilder enderecoLista = new StringBuilder("; ");

            enderecoLista.append(removerAcentosEspacoBranco(end.getTipoLogradouro()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getLogradouro()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getNumero()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getUf()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getCidade()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getCep()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getComplemento()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getPontoReferencia()));
            enderecoLista.append(removerAcentosEspacoBranco(end.getBairro()));

            MessageDigest algorithm = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = algorithm.digest(enderecoLista.toString().getBytes("UTF-8"));


            StringBuilder hash = new StringBuilder();
            for (byte b : messageDigest) {
                hash.append(String.format("%02X", 0xFF & b));
            }

            pairEnd.add(Pair.with(end,hash.toString()));
        }

        return pairEnd.stream().distinct().map(x->x.getValue0()).toList();
    }
    public String removerAcentosEspacoBranco(String str) {
        return Normalizer.normalize(str.toUpperCase(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
