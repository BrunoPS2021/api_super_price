package com.app.restApiAndroid.models.dots;

import com.app.restApiAndroid.enums.UserRole;
import com.app.restApiAndroid.models.usuario.DadosUsuario;

public record RegisterDTO(String login, String password, UserRole role, DadosUsuario dadosUsuario) {

}
