package com.app.restApiAndroid.repositories;

import com.app.restApiAndroid.models.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    UserDetails findByLogin(String login);

    List<Usuario> findAllByLogin(String login);

}
