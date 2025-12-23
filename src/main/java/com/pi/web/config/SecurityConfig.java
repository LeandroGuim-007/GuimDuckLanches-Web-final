package com.pi.web.config;

import com.pi.web.data.LoginEntity;
import com.pi.web.data.LoginRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/css/**", "/bootstrap/**", "/imagens/**").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/caixa/**").hasAnyRole("VENDEDOR", "ADMIN")
                .anyRequest().authenticated()
                )
                .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/cardapio", true)
                .permitAll()
                )
                .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner initUsuarios(LoginRepository loginRepository,
            PasswordEncoder passwordEncoder) {

        return args -> {

            criarUsuarioSeNaoExistir(
                    loginRepository,
                    passwordEncoder,
                    "Admin",
                    "admin123",
                    "admin"
            );

            criarUsuarioSeNaoExistir(
                    loginRepository,
                    passwordEncoder,
                    "Vendedor",
                    "vendedor123",
                    "vendedor"
            );

            criarUsuarioSeNaoExistir(
                    loginRepository,
                    passwordEncoder,
                    "Usuario",
                    "usuario123",
                    "usuario"
            );
        };
    }

    private void criarUsuarioSeNaoExistir(
            LoginRepository repo,
            PasswordEncoder encoder,
            String login,
            String senha,
            String nivelAcesso) {

        if (repo.findByLogin(login).isEmpty()) {
            LoginEntity usuario = new LoginEntity();
            usuario.setLogin(login);
            usuario.setSenha(encoder.encode(senha));
            usuario.setNivelAcesso(nivelAcesso);

            repo.save(usuario);
        }
    }
}
