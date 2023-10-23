package com.app.restApiAndroid.repositories;

import com.app.restApiAndroid.models.usuario.DadosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DadosUsuarioRepository extends JpaRepository<DadosUsuario,String> {
    DadosUsuario findByName(String name);

    List<DadosUsuario> findAllByNameContaining(String name);

    List<DadosUsuario> findAllByUserId(String usersId);
    DadosUsuario findByUserId(String usersId);

    List<DadosUsuario> findAllByUserLogin(String login);

    DadosUsuario findByUserLogin(String login);

    List<DadosUsuario> findAllByEmail(String email);

    DadosUsuario findByEmail(String email);
}
