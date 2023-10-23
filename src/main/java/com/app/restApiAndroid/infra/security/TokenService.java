package com.app.restApiAndroid.infra.security;

import com.app.restApiAndroid.models.usuario.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${security.token.secret}")
    private String secret;
    public String generateToken(Usuario user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getLogin())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        }catch (JWTCreationException jwtCreationException){
            throw new RuntimeException("Error while generating token", jwtCreationException);
        }
    }

    public String validateToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException jwtVerificationException){
            return "";
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(20).toInstant(ZoneOffset.of("-03:00"));
    }
}
