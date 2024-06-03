// Pacote DTO da API

package projeto.java.api.dto;

import org.springframework.beans.BeanUtils;
import projeto.java.api.entity.LoginEntity;

public class LoginDTO {
    private Long id;
    private String email;
    private String senha;

    public LoginDTO(LoginEntity usuario) {
        BeanUtils.copyProperties(usuario, this);
    }
    public LoginDTO() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
