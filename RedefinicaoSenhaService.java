// CLASSE DO PACOTE service DA API

package projeto.java.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.java.api.entity.LoginEntity;
import projeto.java.api.entity.TokenRedefinicaoSenha;
import projeto.java.api.repository.LoginRepository;
import projeto.java.api.repository.TokenRedefinicaoSenhaRepository;

import java.util.UUID;

@Service
public class RedefinicaoSenhaService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private TokenRedefinicaoSenhaRepository tokenRepository;

    public void solicitarRedefinicaoSenha(String email){
        LoginEntity loginEntity = loginRepository.findByEmail(email);
        if (loginEntity != null) {
            TokenRedefinicaoSenha token = new TokenRedefinicaoSenha();
            token.setLoginEntity(loginEntity);
            token.setToken(UUID.randomUUID().toString());
            tokenRepository.save(token);


        }
    }
    public void redefinirSenha(String token, String novaSenha){
        TokenRedefinicaoSenha tokenRedefinicaoSenha = tokenRepository.findByToken(token);
        if (tokenRedefinicaoSenha != null) {
            LoginEntity loginEntity = tokenRedefinicaoSenha.getLoginEntity();
            loginEntity.setSenha(novaSenha);
            loginRepository.save(loginEntity);
            tokenRepository.delete(tokenRedefinicaoSenha);
        }
    }
}
