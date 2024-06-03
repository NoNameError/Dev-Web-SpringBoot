//CLASSE DO PACOTE controller DA API 

package projeto.java.api.controller;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import projeto.java.api.service.RedefinicaoSenhaService;

@RestController
@RequestMapping("/redefinir-senha")
public class RedefinirSenhaController {

    @Autowired
    private RedefinicaoSenhaService redefinicaoSenhaService;

    @PostMapping("/solicitar")
    public ResponseEntity<String> solicitarRedefinicaoSenha(@RequestParam String email){
        redefinicaoSenhaService.solicitarRedefinicaoSenha(email);
        return ResponseEntity.ok("Solicitação de redefinição de senha recebida. Verifique seu e-mail.");
    }
    @PostMapping("/confirmar")
    public ResponseEntity<String> confirmarRedefinicaoSenha(@RequestParam String token, @RequestParam String novaSenha){
        redefinicaoSenhaService.redefinirSenha(token,novaSenha);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }
}
