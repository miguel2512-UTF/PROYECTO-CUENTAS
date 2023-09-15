package com.cuentas.proyectocuentas.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;


class UnauthenticatedRequestHandler implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String contextPath = request.getContextPath();
        if (request.getServletPath().startsWith("/usuario")) {
            response.sendRedirect(contextPath + "/login");
        } else if (request.getServletPath().startsWith("/compromiso")) {
            response.sendRedirect(contextPath + "/login");
        } else if (request.getServletPath().startsWith("/tipocompromiso")) {
            response.sendRedirect(contextPath + "/login");
        } else if (request.getServletPath().startsWith("/prestamo")) {
            response.sendRedirect(contextPath + "/login");
        } else if (request.getServletPath().startsWith("/prestamoabono")) {
            response.sendRedirect(contextPath + "/login");
        } else if (request.getServletPath().startsWith("/pagoprestamo")) {
            response.sendRedirect(contextPath + "/login");
        } else if (request.getServletPath().startsWith("/inicio")) {
            response.sendRedirect(contextPath + "/login");
        } else {
            response.sendError(404);
        }
    }
}