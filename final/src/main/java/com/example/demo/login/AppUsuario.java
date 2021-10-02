package com.example.demo.login;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Table(name = "Usuarios")
@Entity
public class AppUsuario implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre, usuario, email, contrasena;
    //EnumType: Contiene los valores de usuario
    @Enumerated(EnumType.STRING)
    private AppUsuarioRol appUsuarioRol;

    public AppUsuario() {
    }

    public AppUsuario(String nombre, String usuario, String email, String contrasena, AppUsuarioRol appUsuarioRol) {
        this.nombre = nombre;
        this.usuario = usuario;
        this.email = email;
        this.contrasena = contrasena;
        this.appUsuarioRol = appUsuarioRol;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public AppUsuarioRol getAppUserRole() {
        return appUsuarioRol;
    }

    public void setAppUserRole(AppUsuarioRol appUsuarioRol) {
        this.appUsuarioRol = appUsuarioRol;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(appUsuarioRol.name());
        return Collections.singletonList(simpleGrantedAuthority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", usuario='" + usuario + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", appUserRole=" + appUsuarioRol +
                '}';
    }
}
