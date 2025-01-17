package dio.cadastro_produtos.auth.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dio.cadastro_produtos.auth.entity.User;
import dio.cadastro_produtos.auth.repository.UserRepository;
import dio.cadastro_produtos.config.jwt.JwtRequest;
import dio.cadastro_produtos.config.jwt.JwtTokenUtil;


@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public User save(User User) {
        return repository.save(User);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Usuário não encontrado: " + username);
        }
        return user;
    }

    public ResponseEntity<?> signin(JwtRequest authenticationRequest, AuthenticationManager authenticationManager) {
        try {

            System.out.println("Tentando autenticar o usuário: " + authenticationRequest.getUsername());
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getSenha(), authenticationManager);
            System.out.println("Autenticação bem-sucedida!");

            final User userDetails = repository.findByUsername(authenticationRequest.getUsername());
            System.out.println(userDetails);
            System.out.println(userDetails.getUsername());
            System.out.println(userDetails.getSenha());
            System.out.println(userDetails.getRoles());

            System.out.println("Tentando gerar token");
            final String token = jwtTokenUtil.generateToken(userDetails);
            System.out.println("seu token: " + token);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.ok(e.getMessage());
        }
    }

    private void authenticate(String username, String password, AuthenticationManager authenticationManager) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USUARIO DESABILITADO", e);
        } catch (BadCredentialsException e) {
            throw new Exception("CREDENCIAIS INVALIDAS", e);
        }
    }

    public User findByUsername(String username) {
        return repository.findByUsername(username);
    }
}
