//CLASSE DO PACOTE service DA API

package projeto.java.api.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import projeto.java.api.entity.LoginEntity;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private Long id;
    private  String email;
    private String username;
    private String senha;

    public UserDetailsImpl( Long id,String email,String senha,
                            Collection<? extends GrantedAuthority> authorities){
        super();
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(LoginEntity usuario){
        return new UserDetailsImpl(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getSenha(),
                new ArrayList<>());
    }

    private Collection<? extends GrantedAuthority> authorities;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }

}
