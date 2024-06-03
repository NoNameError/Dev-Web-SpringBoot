// CLASSE DO PACOTE jwt DENTRO DO PACOTE security DA API

package projeto.java.api.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import projeto.java.api.service.UserDetailsServiceImpl;

import java.io.IOException;
import java.security.Security;

public class AuthFilterToken extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
            try {
                String jwt = getToken(request);
                if(jwt != null && jwtUtils.validateJwtToken(jwt)){
                    String username = jwtUtils.getUsernameToken(jwt);
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }catch (Exception e){
                System.out.println("Ocorreu um erro ao processar o token");
            }finally {

            }
            filterChain.doFilter(request, response);
    }
    private String getToken(HttpServletRequest request){
        String headerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer")){
            return headerToken.replace("Bearer ","");
        }
        return null;
    }
}
