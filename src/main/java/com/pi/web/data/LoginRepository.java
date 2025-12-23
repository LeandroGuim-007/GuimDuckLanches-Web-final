package com.pi.web.data;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginEntity, Integer>{
    Optional<LoginEntity> findByLoginAndSenha(String login, String senha);
    
    Optional<LoginEntity> findByLogin(String login);
}
