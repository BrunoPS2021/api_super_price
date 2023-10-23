package com.app.restApiAndroid.infra.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

@ControllerAdvice
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        // 401 ou 500 ou 501
        if(response.getStatus() < 600 && response.getStatus() >= 500)
        {
            response.setContentType("application/json");
            response.getWriter().write("Erro não mapeado: "+authException.getMessage());
        }
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("Token não autorizado ou token vazio!");
        }

    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AccessDeniedException accessDeniedException) throws IOException {
        // 403
        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Authorization Failed : " + accessDeniedException.getMessage());
    }

    @ExceptionHandler (value = {Exception.class})
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         Exception exception) throws IOException {
        // 500
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Internal Server Error : " + exception.getMessage());
    }
}
