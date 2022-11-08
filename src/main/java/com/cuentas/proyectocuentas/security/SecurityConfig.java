package com.cuentas.proyectocuentas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    private UserDetailsServiceImpl usuarioDetails;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UnauthenticatedRequestHandler unauthenticatedRequestHandler() {
        return new UnauthenticatedRequestHandler();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioDetails).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/assets/**","/usuario/**").permitAll().antMatchers("/inicio","/compromiso/**","/tipocompromiso/**","/prestamo/**","/pagoprestamo/**","/prestamoabono/**").hasAnyAuthority("ADMINISTRADOR","CONTADOR","ADMIN DEL HOGAR").anyRequest().authenticated()

        .and()
            .formLogin().loginPage("/login")
            .loginProcessingUrl("/loginpost").defaultSuccessUrl("/inicio", true).usernameParameter("correoUsuario").passwordParameter("contrasenaUsuario").permitAll()
        .and()
            .exceptionHandling().authenticationEntryPoint(unauthenticatedRequestHandler())
        .and()
            .logout().logoutUrl("/logout").logoutSuccessUrl("/");
    }
    
}
