//CLASSE DO PACOTE repository DA API

package projeto.java.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import projeto.java.api.entity.TokenRedefinicaoSenha;

public interface TokenRedefinicaoSenhaRepository extends JpaRepository<TokenRedefinicaoSenha ,Long> {
    TokenRedefinicaoSenha findByToken(String token);
}
