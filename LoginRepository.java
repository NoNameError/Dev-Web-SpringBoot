package projeto.java.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.api.entity.LoginEntity;

import java.util.Optional;

public interface  LoginRepository extends JpaRepository<LoginEntity, Long> {
    Optional<LoginEntity> findByLogin(String login);
}
