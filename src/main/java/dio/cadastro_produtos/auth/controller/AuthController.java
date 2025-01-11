package dio.cadastro_produtos.auth.controller;

import dio.cadastro_produtos.auth.service.UserService;
import dio.cadastro_produtos.config.jwt.JwtRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticate(@RequestBody JwtRequest jwtRequest) {
        return userService.signin(jwtRequest, authenticationManager);
    }

}
