package com.pi.web.service;

import com.pi.web.data.LoginEntity;
import com.pi.web.data.LoginRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    LoginRepository loginRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;

    public List<LoginEntity> listarLogin() {
        return loginRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        LoginEntity usuario = loginRepository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        String role = "ROLE_" + usuario.getNivelAcesso().toUpperCase();

        return User.builder().username(usuario.getLogin()).password(usuario.getSenha()).authorities(role).build();
    }

    public void migrarSenhas() {
        loginRepository.findAll().forEach(login -> {

            // evita criptografar duas vezes
            if (!login.getSenha().startsWith("$2a$")) {
                String senhaCriptografada
                        = passwordEncoder.encode(login.getSenha());

                login.setSenha(senhaCriptografada);
                loginRepository.save(login);
            }
        });
    }
}
