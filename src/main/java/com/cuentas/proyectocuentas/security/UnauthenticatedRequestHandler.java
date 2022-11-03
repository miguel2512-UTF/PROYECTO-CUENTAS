package com.cuentas.proyectocuentas.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


class UnauthenticatedRequestHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        if (request.getServletPath().startsWith("/usuario/")) {
            response.sendRedirect("/proyecto/login");
        } else if (request.getServletPath().startsWith("/compromiso/")) {
            response.sendRedirect("/proyecto/login");
        } else if (request.getServletPath().startsWith("/tipocompromiso/")) {
            response.sendRedirect("/proyecto/login");
        } else if (request.getServletPath().startsWith("/prestamo/")) {
            response.sendRedirect("/proyecto/login");
        } else if (request.getServletPath().startsWith("/prestamoabono/")) {
            response.sendRedirect("/proyecto/login");
        } else if (request.getServletPath().startsWith("/pagoprestamo/")) {
            response.sendRedirect("/proyecto/login");
        } else if (request.getServletPath().startsWith("/inicio")) {
            response.sendRedirect("/proyecto/login");
        } else {
            response.sendError(404);
        }
    }
}