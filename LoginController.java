package projeto.java.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.java.api.dto.LoginDTO;
import projeto.java.api.service.LoginService;

import java.util.List;

@RestController
@RequestMapping(value="/usuario")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public List<LoginDTO> listarTodos(){
        return loginService.listarTodos();
    }
    @PostMapping
    public void inserir(@RequestBody LoginDTO usuario) {
        loginService.inserir(usuario);
    }
    @PutMapping
    public LoginDTO alterar(@RequestBody LoginDTO usuario) {
        return loginService.alterar(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        loginService.excluir(id);
        return ResponseEntity.ok().build();
    }
}
