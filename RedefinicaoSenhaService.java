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

    @Autowired
    private EmailService emailService;

    public void solicitarRedefinicaoSenha(String email){
        LoginEntity loginEntity = loginRepository.findByEmail(email);
        if (loginEntity != null) {
            TokenRedefinicaoSenha token = new TokenRedefinicaoSenha();
            token.setLoginEntity(loginEntity);
            token.setToken(UUID.randomUUID().toString());
            tokenRepository.save(token);
            // LINK DO SITE
            String link = "http://<seusite>.com/redefinir-senha/confirmar?token=" + token.getToken();
            emailService.enviarEmail(email, "Redefinição de Senha", "Clique no link para redefinir sua senha: " + link);
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
