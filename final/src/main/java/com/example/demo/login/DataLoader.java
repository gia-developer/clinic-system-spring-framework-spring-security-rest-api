package com.example.demo.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private IUsuarioRepository usuarioRepository;

    @Autowired
    public DataLoader(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String contrasena = bCryptPasswordEncoder.encode("contrasena");

        usuarioRepository.save(new AppUsuario("Gianna", "gianna0723", "gianna.belen.russo@gmail.com", contrasena, AppUsuarioRol.ADMIN));
        usuarioRepository.save(new AppUsuario("Gianna2", "gianna07", "gianna@gmail.com", contrasena, AppUsuarioRol.USER));
    }
}
