// CLASSE DO PACOTE service DA API

package projeto.java.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import projeto.java.api.dto.LoginDTO;
import projeto.java.api.entity.LoginEntity;
import projeto.java.api.repository.LoginRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public List<LoginDTO> listarTodos() {
        List<LoginEntity> usuarios = loginRepository.findAll();
        return usuarios.stream().map(LoginDTO::new).toList();
    }

    public void inserir(LoginDTO usuario) {
        LoginEntity loginEntity = new LoginEntity(usuario);
        loginEntity.setSenha(passwordEncoder.encode(usuario.getSenha()));
        loginRepository.save(loginEntity);

        // ENVIAR UM EMAIL
        emailService.enviarEmail(usuario.getEmail(), "Seja Bem vindo","Voce esta recebendo email teste");
    }

    public LoginDTO alterar(LoginDTO usuario) {
        LoginEntity usuarioEntity = new LoginEntity(usuario);
        return new LoginDTO(loginRepository.save(usuarioEntity));
    }

    public void excluir(Long id) {
        Optional<LoginEntity> usuario = loginRepository.findById(id);
        usuario.ifPresent(loginRepository::delete);
    }

    public LoginDTO buscarPorId(Long id) {
        Optional<LoginEntity> usuario = loginRepository.findById(id);
        return usuario.map(LoginDTO::new).orElse(null); // ou lançar uma exceção se preferir
    }
}
