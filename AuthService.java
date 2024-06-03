// CLASS DO PACOTE service DA API

package projeto.java.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import projeto.java.api.dto.AcessDTO;
import projeto.java.api.dto.AuthenticationDTO;
import projeto.java.api.security.jwt.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public AcessDTO login(AuthenticationDTO authDto){
        try {
            // cria mecanismo de credencial para o spring
            UsernamePasswordAuthenticationToken userAuth = new UsernamePasswordAuthenticationToken(authDto.getEmail(),authDto.getSenha());
            // prepara mecanismo para autenticacao
            Authentication authentication = authenticationManager.authenticate(userAuth);
            //busca usuario logado
            UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

            String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

            AcessDTO acessDto = new AcessDTO(token);

            return acessDto;
        }catch (BadCredentialsException e){
            //
        }
        return null;
    }
}
